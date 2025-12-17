package com.l;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Test20 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(1);
//        System.out.println(i.incrementAndGet());//++i. 1
//        System.out.println(i.getAndIncrement());//i++. 1
//
//        System.out.println(i.getAndAdd(2));
//        System.out.println(i.addAndGet(2));

//        //.           读取到     设置值
        i.updateAndGet(x -> x * 10);
//        System.out.println(i.get());

        //先定义实现逻辑在通过传参形式执行操作
        // 传了个匿名类
        int i1 = updateAndGet(i, p -> p * 10);
        System.out.println(i1);

    }

    public static int updateAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while (true) {
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if (i.compareAndSet(prev, next)) {
                return next;
            }
        }
    }

}
