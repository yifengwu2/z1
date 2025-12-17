package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test11 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.debug("t1等待3秒未拿到锁");
                    return;
                }
            } catch (InterruptedException e) {
                log.debug("t1在等锁的时候被打断，");
                e.printStackTrace();
                return;
            }

            try {
                //拿到锁
                log.debug("t1拿到锁");

            } finally {
                lock.unlock();
            }

        }, "t1");

        lock.lock();
        log.debug("获得到锁了");
        t1.start();

        Thread.sleep(2000);
        lock.unlock();
    }
}
