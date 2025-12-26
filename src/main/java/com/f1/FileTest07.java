package com.f1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 定义一个方法找某一个文件夹中，是否以avi结尾的电影
 */
public class FileTest07 {
    public static void isTrue(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".avi")) {
                System.out.println(f.getName());
            }
        }
    }

    public static void hasAVI(Path path) throws IOException {
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile)
                    .forEach(path1 -> {
                        boolean success = path1.getFileName().toString().endsWith(".avi");
                        if (success) {
                            System.out.println("找到avi"+path1);
                        }else {
                            System.out.println("不匹配"+path1.getFileName());
                        }
                    });
        }

    }

    public static void main(String[] args) throws IOException {
//        FileTest07.isTrue("/Users/Zhuanz/Documents/java01/code/code/aaa");
        Path path = Paths.get("/Users/Zhuanz/Documents/java01/code/code/a");
        hasAVI(path);


    }
}
