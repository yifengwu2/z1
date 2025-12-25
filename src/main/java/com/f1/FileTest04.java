package com.f1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTest04 {
    public static void main(String[] args) throws IOException {
        String str = "/Users/Zhuanz/Documents/java01/code/code/z1/a.txt";
        try (FileInputStream fis = new FileInputStream(str)) {
            int read;
            while ((read = fis.read())!=-1) {
                System.out.print((char) read);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
