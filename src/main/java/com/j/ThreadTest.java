package com.j;

public class ThreadTest {
    public static void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running.......");
            }
        });
        t.start();
    }

    public static void test2() {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("running.....");
            }
        };
        t.start();
    }

    public static void main(String[] args) {

    }

}
