package com.j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class CaseInsensitiveSearch {

    public static void InsensitiveSearch() {
        Properties properties = new Properties();
        try (InputStream in = CaseInsensitiveSearch.class.getClassLoader().getResourceAsStream("config.properties");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {

            if (in == null) {
                System.err.println("读取配置文件失败");
                return;
            }

            properties.load(reader);

            String dir = properties.getProperty("directory", ".");
            String content = properties.getProperty("keyword", "");
            boolean ignoreCase = Boolean.parseBoolean(properties.getProperty("ignoreCase", "false"));

            System.out.println(" 正在搜索包含 \"todo\" 的文件（忽略大小写）...");
            if (ignoreCase) {
                System.out.println("忽略大小写");
            }

            try (Stream<Path> stream = Files.walk(Path.of(dir))) {
                stream.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .forEach(p -> {
                            try {
                                List<String> list = Files.readAllLines(p);
                                for (String s : list) {
                                    if (ignoreCase) {
                                        if (s.toLowerCase().contains(content)) {
                                            System.out.println(p);
                                            System.out.println(s);
                                        }
                                    } else {
                                        if (s.contains(content)) {
                                            System.out.println(p);
                                            System.out.println(s);
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("读取失败" + e.getMessage());
                            }


                        });

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void SearchWithCount() {
        Properties properties = new Properties();
        try (InputStream in = CaseInsensitiveSearch.class.getClassLoader().getResourceAsStream("config.properties");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            if (in != null) {
                properties.load(reader);
            } else {
                System.out.println("文件读取失败");
                return;
            }
            String directory = properties.getProperty("directory");
            String keyword = properties.getProperty("keyword");

            try (Stream<Path> stream = Files.walk(Path.of(directory))) {
                stream.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .forEach(path -> {
                            int matchCount = 0;
                            try {
                                List<String> list = Files.readAllLines(path);
                                for (String s : list) {
                                    if (s.contains(keyword)) {
                                        matchCount++;
                                        System.out.println(path + ": " + matchCount + s.trim());
                                    }
                                }

                            } catch (IOException e) {
                                System.out.println("文件不存在或读取失败");
                            }


                        });
            }
        } catch (IOException e) {
            System.out.println("读取配置文件失败" + e.getMessage());
        }


    }

    public static void SaveResult() {
        Properties properties = new Properties();
        try (InputStream in = CaseInsensitiveSearch.class.getClassLoader().getResourceAsStream("config.properties");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            properties.load(reader);

            String directory = properties.getProperty("directory", ".");
            String keyword = properties.getProperty("keyword");

            List<String> res = new ArrayList<>();

            try (Stream<Path> stream = Files.walk(Path.of(directory))) {
                stream.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .forEach(p -> {
                            int count = 0;
                            try {
                                List<String> list = Files.readAllLines(p);
                                for (String s : list) {
                                    if (s.contains(keyword)) {
                                        count++;
                                        String content = p + ": " + count + s;
                                        res.add(content);
                                    }
                                }
                                System.out.println("搜索成功🔍" + count + "处内容匹配");

                            } catch (IOException e) {
                                System.out.println("文件读取失败" + e.getMessage());
                            }
                        });

                try {
                    Files.writeString(Path.of("search-result.txt"), res.toString(),
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("内容写入失败" + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("配置文件读取不到" + e.getMessage());
        }
    }

    public static void sikDir() {
        Properties properties = new Properties();
        try (InputStream in = CaseInsensitiveSearch.class.getClassLoader().getResourceAsStream("config.properties");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {

            properties.load(reader);

            String directory = properties.getProperty("directory");
            String keyword = properties.getProperty("keyword");

            try (Stream<Path> stream = Files.walk(Path.of(directory))) {
                stream.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .filter(p -> !p.toString().contains("target"))
                        .filter(p -> !p.toString().contains("build"))
                        .forEach(path -> {
                            try {
                                List<String> list = Files.readAllLines(path);
                                for (String s : list) {
                                    if (s.contains(keyword)) {
                                        System.out.println(path + ": " + s.trim());
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("文件不存在或读取失败");
                            }
                        });
            }
        } catch (IOException e) {
            System.out.println("读取配置文件失败" + e.getMessage());
        }

    }

    public static void searchWithLines() {
        Properties properties = new Properties();
        try (InputStream in = CaseInsensitiveSearch.class.getClassLoader().getResourceAsStream("config.properties");
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {

            properties.load(reader);

            String directory = properties.getProperty("directory");
            String keyword = properties.getProperty("keyword");
            boolean ignoreCase = Boolean.parseBoolean(properties.getProperty("ignoreCase"));
            Boolean b = Boolean.valueOf(ignoreCase ? keyword.toLowerCase() : keyword);

            try (Stream<Path> stream = Files.walk(Path.of(directory))) {
                stream.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .filter(p -> !p.toString().contains("target"))
                        .filter(p -> !p.toString().contains("build"))
                        .forEach(path -> {
                            long[] intCount = {0};

                            //一行一行读取防止读大文件时内存爆炸
                            try (Stream<String> lines = Files.lines(path)) {
                                lines.map(line -> ignoreCase ? line.toLowerCase() : line)
                                        .filter(s -> s.contains(keyword))
                                        .forEach(s -> {
                                            intCount[0]++;
                                            System.out.printf("📄 %s | #%d | %s%n",
                                                    path.getFileName(),
                                                    intCount[0],
                                                    s.trim());
                                        });

                            } catch (IOException e) {
                                System.out.println("文件内容读取失败" + e.getMessage());
                            }

                        });
            }
        } catch (IOException e) {
            System.out.println("读取配置文件失败" + e.getMessage());
        }

    }

    public static long calculateTotalSize(String directoryPath) {
        Path path = Path.of(directoryPath);
        try (Stream<Path> stream = Files.walk(path)) {
            long sum = stream.filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.println("文件读取失败" + e.getMessage());
                        }
                        return 0;
                    }).sum();
            return sum;
        } catch (IOException e) {
            System.out.println("文件读取失败" + e.getMessage());
            return -1;
        }
    }

    public static void findLargestFiles(String directoryPath, int n) {
        Path path = Path.of(directoryPath);
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile)
                    .sorted((p1, p2) -> {
                        try {
                            long size1 = Files.size(p1);
                            long size2 = Files.size(p2);
                            return (int) (size2 - size1);
                        } catch (IOException e) {
                            System.out.println("文件内容获取失败" + e.getMessage());
                        }
                        return 0;
                    }).limit(5).forEach(p -> {
                        try {
                            long size = Files.size(p);
                            String s = size + "->" + p;
                            System.out.println(s);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.out.println("文件读取失败" + e.getMessage());
        }
    }


}
