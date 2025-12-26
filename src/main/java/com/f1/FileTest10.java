package com.f1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * 统计一个文件夹的大小
 * list平铺一层
 *
 */
public class FileTest10 {
    public static long countSize(Path path) throws IOException {
        AtomicLong count = new AtomicLong();
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile).forEach(p -> {
                try {
                    long size = Files.size(path);
                    count.addAndGet(size);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        return count.get();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/Zhuanz/Documents/java01/code/code/aaa");
        System.out.println(FileTest10.countSize(path));
        try(Stream<Path> list = Files.list(path)){
            list.forEach(System.out::println);
        }

    }
}
