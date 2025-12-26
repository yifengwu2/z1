package com.f1;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

public class WatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Path watchDir = Path.of("logs");
        //创建watchService
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            // Step 2：注册目录（支持子目录！自动递归监听）
            watchDir.register(
                    watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,// 新建文件
                    StandardWatchEventKinds.ENTRY_MODIFY,// 修改文件（含追加写入）
                    StandardWatchEventKinds.ENTRY_DELETE// 删除文件
            );
            System.out.println("已开始监听目录" + watchDir.toAbsolutePath());

            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path filename = (Path) event.context(); // ← 注意：是相对路径！
                    Path fullPath = watchDir.resolve(filename);

                    CompletableFuture.runAsync(() -> {
                        System.out.printf("[%s] %s → %s%n",
                                kind.name(),
                                filename,
                                fullPath
                        );
                        if (filename.toString().toLowerCase().endsWith(".log")) {
                            try {
                                long size = Files.size(fullPath);
                                if (size >= 100L * 1024 * 1024) {
                                    System.out.printf("⚡ 大日志发现！%s (%.1f MB)%n",
                                            filename, size / (1024.0 * 1024.0));

                                }
                            } catch (IOException e) {
                                System.out.println(e.getMessage());

                            }
                        }
                    });
                    boolean valid = key.reset();
                    if (!valid) {
                        System.out.println("监听器失效，请检查目录是否存在或权限");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("目录不存在或无权限：" + e.getMessage());
        }

    }
}
