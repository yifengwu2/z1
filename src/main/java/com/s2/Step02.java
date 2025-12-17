package com.s2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.s2.Step01.Sleeper;

public class Step02 {
    public static void main(String[] args) {
        System.out.println("== 方式1：用 new Thread() ==");
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            new Thread(() -> {
                System.out.println("线程任务->" + taskId + "，线程名:" + Thread.currentThread().getName());
                Sleeper(5);
                System.out.println("线程任务->" + taskId + "完成");
            }, "t" + i).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n=== 方式2：用 ExecutorService ===");
        AtomicInteger tNumber = new AtomicInteger(1);

        ThreadFactory t = r -> new Thread(r, "线程" + tNumber.getAndIncrement());


        ExecutorService executor = Executors.newFixedThreadPool(2, t);
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "->" + "正在执行" + "任务" + taskId);
                        try {
                            Thread.sleep(500);
                            System.out.println("任务" + taskId + "执行完成");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println(e.getMessage());
                        }
                    }
            );
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdown();
                if (executor.awaitTermination(2, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }


    }
}
