package com.f1;

import java.io.*;

/**
 * 1.参数传字符串和文件都是可以的
 * 2.文件不存在会创建一个新的文件，但要保证父级路径存在
 * 3.如果文件已存在，会清空文件
 *
 * 写数据：write方法的参数是整数，但是实际上写到本地文件中的是整数在ASCII对应的字符
 *
 *
 */
public class FileTest02 {
    public static void main(String[] args) throws IOException {
        String str = "/Users/Zhuanz/Documents/java01/code/code/z1/a.txt";
        try (FileOutputStream fos = new FileOutputStream(str)) {
            fos.write(97);
            fos.write(98);
            fos.write(99);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
