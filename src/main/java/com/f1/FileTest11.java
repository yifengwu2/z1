package com.f1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Arrays;

/**
 * 四种拷贝方式，并统计各自用时
 * 字节流基本流：一次读写一个字节
 * 字节基本流：一次读写一个字节数组
 * 字节缓冲流：一次读写一个字节
 * 字节缓冲流：一次读写一个字节数组
 */
public class FileTest11 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Path path = Path.of("/Users/Zhuanz/Documents/java01/code/code/a/ax.txt");
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            int read;
            byte[] bytes = new byte[2];
            while ((read = fis.read(bytes, 0, bytes.length)) != -1) {
                for (int i = 0; i < bytes.length; i++) {
                    System.out.print((char) bytes[i]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println(end - start);

    }

}
