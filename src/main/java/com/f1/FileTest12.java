package com.f1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 创建app.properties文件，并写入数据
 */
public class FileTest12 {
    public static void writeConfig(Path path, String content) throws IOException {
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.writeString(path, content, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        );
        System.out.println("写入成功");
    }

    public static void main(String[] args) {
        Path path = Path.of("data", "config", "app.properties");
        String content = "port=8080\nenv=prod\n# generated at ";
        String s = "abc";
        try {
            writeConfig(path, content);
            writeConfig(path, s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
