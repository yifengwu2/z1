package com.f1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 如果想要续写，构造中传个true
 * 默认false表示创建对象时会清空文件内容
 */
public class FileTest03 {
    public static void main(String[] args) throws IOException {
        String str = "/Users/Zhuanz/Documents/java01/code/code/z1/a.txt";
        try (FileOutputStream fos = new FileOutputStream(str,true)) {
            String s = "abcde";
            String s1 = "\r";//换行
            byte[] bytes = s.getBytes();
            byte[] bytes1 = s1.getBytes();
            fos.write(bytes);
            fos.write(bytes1);
            fos.write(bytes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
