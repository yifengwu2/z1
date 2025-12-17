package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test14 {
    static final Object lock = new Object();
    static boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
                LockSupport.park();
                log.debug("1");

        }, "t1");

        Thread t2 = new Thread(() -> {
                log.debug("2");
                LockSupport.unpark(t1);

        }, "t2");

        t1.start();
        t2.start();

    }
}
