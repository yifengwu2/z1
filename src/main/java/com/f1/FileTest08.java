package com.f1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * 删除文件夹，含子文件
 */
public class FileTest08 {
    public static void deleteFile(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            System.out.println("目录不存在");
            return;
        }
        if (!Files.isDirectory(dir)) {
            System.out.println("不是目录");
            return;
        }
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.sorted(Collections.reverseOrder())
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                            System.out.println("删除文件" + p.getFileName() + "成功");
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/Zhuanz/Documents/java01/code/code/b");
        deleteFile(path);
    }
}
