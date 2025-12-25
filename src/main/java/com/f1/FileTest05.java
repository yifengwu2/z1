package com.f1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest05 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/xxx/xx/c/movie.mp4");
        FileOutputStream fos = new FileOutputStream("/abc/bb/c/copy.mp4");

        byte[] bytes = new byte[1024 * 2024 * 5];
        int read;
        while ((read = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, bytes.length);
        }
        fos.close();
        fis.close();
    }
}
