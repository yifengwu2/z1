package com.l;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程1输出a5次，线程2输出b5次，线程3输出c5次。现在要求输出abcabcabcabc
 */
@Slf4j
public class Test15 {
    static final Object lock = new Object();
    static int stage = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (stage != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    log.debug("a");
                    stage = 1;
//                    log.debug("a-stage{}", stage);
                    lock.notifyAll();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (stage != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    log.debug("b");
                    stage = 2;
//                    log.debug("b-stage{}", stage);
                    lock.notifyAll();
                }
            }

        }, "t2");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (stage != 2) {
                        try {
//                            log.debug("t3 is alive and waiting... stage={}", stage);
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    log.debug("c");
                    stage = 0;
//                    log.debug("c-stage{}", stage);
                    lock.notifyAll();
                }

            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

