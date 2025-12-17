package com.l;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test10 {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    log.debug("t1拿到锁");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    log.debug("t2拿到锁");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "t2");
        t2.start();
        t1.start();
        log.debug("主线程结束");

    }
}
