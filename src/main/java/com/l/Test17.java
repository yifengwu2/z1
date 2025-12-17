package com.l;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test17 {
    public static void main(String[] args) {
        AwaitSignal aw = new AwaitSignal(5);
        Condition a = aw.newCondition();
        Condition b = aw.newCondition();
        Condition c = aw.newCondition();
        Thread t1 = new Thread(() -> {
            aw.print("a", a, b);
        }, "t1");
        Thread t2 = new Thread(() -> {
            aw.print("b", b, c);
        }, "t2");
        Thread t3 = new Thread(() -> {
            aw.print("c", c, a);
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        //主线程上锁
        aw.lock();
        try {
            a.signal();
        } finally {
            aw.unlock();
        }
    }

}

class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            this.lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                this.unlock();
            }

        }
    }


}
