package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class Philosopher extends Thread {
    private Chopsticks left;
    private Chopsticks right;

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (left.tryLock()) {
                try {
                    //获取到左筷子
//                    log.debug("{}获取到左筷子{}", getName(), left);
                    System.out.println(getName() + "获取到左筷子");
                    if (right.tryLock(100,TimeUnit.MILLISECONDS)) {
                        try {
                            //获取到右筷子
//                            log.debug("获取到右筷子{}", right);
//                            log.info("{}开始吃饭", getName());
                            System.out.println(getName() + "获取到右筷子");
                            System.out.println(getName() + "开始吃饭......");
                            Thread.sleep(50);
                        } finally {
                            right.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;

                } finally {
                    left.unlock();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}

class Chopsticks extends ReentrantLock {
    private final String name;

    public Chopsticks(String name) {
        super(true);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopsticks{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Example01 {
    public static void main(String[] args) {
        Chopsticks a = new Chopsticks("1");
        Chopsticks b = new Chopsticks("2");
        Chopsticks c = new Chopsticks("3");
        Chopsticks d = new Chopsticks("4");
        Chopsticks e = new Chopsticks("5");

        Philosopher t1 = new Philosopher("柏拉图", a, b);
        Philosopher t2 = new Philosopher("亚里士多德", b,c );
        Philosopher t3 = new Philosopher("苏格拉底", c, d);
        Philosopher t4 = new Philosopher("毕达哥拉斯", d, e);
        Philosopher t5 = new Philosopher("赫拉克利特", e, a);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
