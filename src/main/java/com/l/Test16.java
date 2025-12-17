package com.l;

import lombok.extern.slf4j.Slf4j;

public class Test16 {
    public static void main(String[] args) {
        SynWaitNotify sw = new SynWaitNotify(5, 0);
        Thread t1 = new Thread(() -> {
            sw.print("a", 0, 1);
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            sw.print("b", 1, 2);
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            sw.print("c", 2, 0);
        }, "t3");
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

@Slf4j
class SynWaitNotify {
    private int loopNumber;

    private int flag;

    public SynWaitNotify(int loopNumber, int flag) {
        this.loopNumber = loopNumber;
        this.flag = flag;
    }

    public void print(String str, int flag, int next) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (this.flag !=flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.print(str);
                this.flag = next;
                this.notifyAll();
            }
        }
    }
}
