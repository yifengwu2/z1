package com.deadlock.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLiveLock {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            //期望减到0退出循环
            while (count > 0) {
                Sleeper.sleep(1);
                count--;
                log.debug("count:{}", count);
            }
        }, "t1").start();

        new Thread(() -> {
            while (count < 20) {
                Sleeper.sleep(1);
                count++;
                log.debug("count{}", count);
            }
        }, "t2").start();
    }
}
