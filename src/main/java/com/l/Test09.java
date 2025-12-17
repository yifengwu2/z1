package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test09 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("t1尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("t1,没拿到锁,返回");
                return;
            }
            try {
                log.debug("t1拿到锁");
            } finally {
                lock.unlock();
            }

        }, "t1");
        lock.lock();
        t1.start();
        Thread.sleep(1000);
        log.debug("t1正在等待，即将打断他");
        t1.interrupt();


    }
}
