package com.deadlock.v1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestDeadLock {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();

    }

}

@Slf4j(topic = "Philosopher")
class Philosopher extends Thread {
    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //尝试获取左筷子
                if (left.tryLock(3, TimeUnit.SECONDS)) {
                    //尝试获取右筷子
                    try {
                        log.debug("{}获取左筷子", getName());
                        if (right.tryLock(3, TimeUnit.SECONDS)) {
                            eat();
                            right.unlock();
                            left.unlock();
                        } else {
                            left.unlock();
                        }
                    } catch (InterruptedException e) {
                        log.debug("{}尝试获取右筷子时候被打断", getName());
                        left.unlock();
                        log.warn(e.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                log.debug("{}尝试获取左筷子时候被打断", getName());
                log.warn(e.getMessage());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }


    private void eat() {
        log.debug("eating");
        Sleeper.sleep(1);
    }
}


class Chopstick extends ReentrantLock {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + "}";
    }
}

class Sleeper {
    public static void sleep(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
