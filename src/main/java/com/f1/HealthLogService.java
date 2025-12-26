package com.f1;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HealthLogService {
    private static final ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "health-log-scanner"));
    private static final long fiveTime = 1000 * 60 * 5;
    private final Path path;

    public HealthLogService(Path path) {
        this.path = path;
    }

    public void findAndGet(Path path) throws IOException {
        try (Stream<Path> stream = Files.walk(path, FileVisitOption.FOLLOW_LINKS)) {
            long size_100_MB = 100L * 1024 * 1024;
            List<Map.Entry<Path, Long>> collect = stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".log"))
                    .map(p -> {
                        try {
                            return Map.entry(p, Files.size(p));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .filter(e -> e.getValue() >= size_100_MB)
                    .sorted(Map.Entry.<Path, Long>comparingByValue().reversed())
                    .limit(5)
                    .collect(Collectors.toList());
            collect.forEach(entry -> {
                long mb = entry.getValue() / (1024L * 1024L);
                long gb = entry.getValue() / (1024L * 1024L * 1024L);
                String sizeStr = gb > 0 ? gb + "GB" : mb + "MB";
                System.out.printf("%d. %s → %s%n",
                        collect.indexOf(entry) + 1,
                        entry.getKey().getFileName(),
                        sizeStr
                );
            });
        }
    }

    public void start() {
        //启动后30秒执行
        pool.scheduleAtFixedRate(() -> {
            try {
                findAndGet(path);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }, 30, fiveTime, TimeUnit.MILLISECONDS);
    }

    //注册一个监听器
    public void startWatch() {
        CompletableFuture.runAsync(() -> {
            System.out.println("WatchService 已启动，监听路径：" + path.toAbsolutePath());
            try (WatchService watch = FileSystems.getDefault().newWatchService()) {
                path.register(watch,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY
                );

                while (!Thread.currentThread().isInterrupted()) {
                    WatchKey key;
                    try {
                        key = watch.take();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        Path filename = (Path) event.context();
                        Path fullPath = path.resolve(filename);

                        if (fullPath.toString().toLowerCase().endsWith(".log")) {
                            System.out.printf("[%s] %s -> %s%n",
                                    kind.name(),
                                    filename.getFileName(),
                                    filename.toAbsolutePath()
                            );
                            long size = Files.size(fullPath);
                            if (size >= 100L * 1024 * 1024) {
                                System.out.printf("⚡ 大日志发现！%s (%.1f MB)%n",
                                        filename, size / (1024.0 * 1024.0));
                            }

                        }
                        boolean reset = key.reset();
                        if (!reset) {
                            System.out.println("监听器失效，请检查目录是否存在或权限");
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }, Executors.newSingleThreadExecutor());

    }

    public void close() throws InterruptedException {
        if (pool == null || pool.isTerminated()) {
            return;
        }
        pool.shutdown();

        if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
            pool.shutdownNow();
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("HealthLogService shutdown failed.");
            }
        }
    }

    public static void main(String[] args) {
        Path path = Path.of("logs");
        HealthLogService logService = new HealthLogService(path);
        try {
            logService.findAndGet(path);
            logService.start();
            logService.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
