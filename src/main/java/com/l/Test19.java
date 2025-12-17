package com.l;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test19 {
    //易变
    static boolean run = true;
    //锁对象
    final static Object lock = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                //...
                synchronized (lock) {
                    if (!run) {
                        break;
                    }
                }
            }
        });

        t.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        log.debug("停止t");
        synchronized (lock) {
            run = false;//线程t不会如预想的停下来
        }
    }

}
