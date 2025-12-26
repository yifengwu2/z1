package com.f1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 统计一个文件夹中的每种文件的个数并打印
 */
public class FileTest09 {
    public static Map<String, Integer> count(Path path) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile).forEach(p -> {
                String string = p.getFileName().toString();
                String[] split = string.split("\\.");
                String s = split[1];
                map.put(s, map.getOrDefault(s, 0) + 1);
            });
        }
        return map;
    }

    public static void main(String[] args) {
        Path path = Paths.get("/Users/Zhuanz/Documents/java01/code/code/aaa");
        Map<String, Integer> count = null;
        try {
            count = FileTest09.count(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(count);
    }
}
