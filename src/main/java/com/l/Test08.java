package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "Test08;")
public class Test08 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            boolean locked = false;
            try {
                //如果没有竞争那么此方法就会获取lock对象锁
                //如果有竞争就进入阻塞队列，可被其他线程用interrupt方法打断
                lock.lockInterruptibly();//可能抛异常

                locked = true;//只有到这里才算真正持有锁
                //接下去在执行临界区
            } catch (InterruptedException e) {
                log.debug("t1中断，放弃抢锁");
                System.out.println(e.getMessage());
                //不需要 unlock！因为没拿到锁
            } finally {
                if (locked) {
                    lock.unlock();
                }
            }
        }, "t1");
        t1.start();

    }
}
