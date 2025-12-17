package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class Test21 {
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) {
        log.debug("main start....");
        //获取值 "A"
        //不能判断 这个共享变量被其他线程修改过
        //AtomicReference他察觉不到共享变量已被修改过
        //希望他感知到就得换AtomicStampReference，加了个版本号
        String prev = ref.getReference();
        //获取版本号
        int stamp = ref.getStamp();
        log.debug("{}", stamp);
        other();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        //尝试改成C
        log.debug("{}",stamp);
        log.debug("change A->C {}", ref.compareAndSet(prev, "C", stamp, stamp + 1));

    }

    private static void other() {
        new Thread(() -> {
            int stamp = ref.getStamp();
            log.debug("{}",stamp);
            log.debug("change A->B {}", ref.compareAndSet(ref.getReference(), "B", stamp, stamp + 1));
        }, "t1").start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        new Thread(() -> {
            int stamp = ref.getStamp();
            log.debug("{}",stamp);
            log.debug("change B->A {}", ref.compareAndSet(ref.getReference(), "A", stamp, stamp + 1));
        }, "t2").start();
    }
}
