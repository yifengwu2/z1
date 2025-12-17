package com.l;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 先打印2后打印1
 */
public class Test12 {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition waitROOM1 = lock.newCondition();

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            int id = i;
            new Thread(() -> {
                lock.lock();
                try {
                    if (id % 2 == 1) {
                        try {
                            waitROOM1.await();
                            System.out.println(1);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(2);
                        waitROOM1.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }

    }

}
