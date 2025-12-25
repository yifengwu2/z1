package com.f1;

import java.io.File;

/**
 * 什么是io流，存储和读取数据的解决方案
 * io流作用：用于读写数据
 * io流向可以分为两种流
 * 程序到文件 输出流（写）
 * 文件到程序 输入流（读）
 * md，txt，xml，lrc文件可以用字符流操作
 * docx，excel要用字节流操作
 * 字节流可以操作所有类型的文件
 * 字符流只能操作纯文本文件
 */
public class FileTest {
    public static void main(String[] args) {
        String parent = "/Users/Zhuanz/Documents/java01/code/code";//父级路径
        String child = "a.txt";//子级路径

//        File file = new File(str);
//        File file = new File(parent, child);
//        System.out.println(file);// /Users/Zhuanz/Documents/java01/code/code/a.txt

        /*System.out.println(file.delete());
        String str2="/Users/Zhuanz/Documents/java01/code/code/student.txt";
        File f = new File(str2);
        System.out.println(f);// Users/Zhuanz/Documents/java01/code/code/student.txt
        System.out.println(f.delete());//删除文件
        System.out.println(f);
*/
        //把一个父级file文件和子级String拼接
//        File p = new File(parent);
//        File file = new File(p, child);
//        System.out.println(file.isDirectory());//false
//        System.out.println(file.isFile());//true
//        System.out.println(file.getAbsolutePath());// Users/Zhuanz/Documents/java01/code/code/a.txt
//        System.out.println(file.getName());// a.txt
//        System.out.println(file.exists());// true
//        System.out.println(file.getParentFile());// Users/Zhuanz/Documents/java01/code/code
//        System.out.println(file.getPath());// /Users/Zhuanz/Documents/java01/code/code/a.txt
//        System.out.println(file.lastModified());//1766662731893
//        File[] files = file.listFiles();
//        for (File f :files){
//            System.out.println(f);
//        }
        File file = new File("myFile/a.txt");
        System.out.println(file);
        System.out.println(file.getAbsolutePath());// /Users/Zhuanz/Documents/java01/code/code/myFile/a.txt
        System.out.println(file.getPath());//myFile/a.txt括号是什么返回就是什么
        System.out.println(file.exists());

        File f = new File("/Users/Zhuanz/Documents/java01/code/code/aaa");
//        System.out.println(f.mkdir());
//        System.out.println(f.mkdirs());
        //有内容的文件夹，删除失败不走回收站
        //文件直接删除
//        System.out.println(f.delete());
        for (File listFile : f.listFiles()) {
            System.out.println(listFile);
        }

    }
}
