package com.f1;

import java.io.File;
import java.io.IOException;

/**
 * 在当前模块下的aaa文件夹中创建一个a.txt文件
 */
public class FileTest06 {
    public static void main(String[] args) throws IOException {
        String s = "/Users/Zhuanz/Documents/java01/code/code/aaa";
        String s2 = "a.txt";
        File file = new File(s, s2);
        if (file.createNewFile()) {
            System.out.println("创建成功...");
        } else {
            System.out.println("创建失败...");
        }

    }
}
