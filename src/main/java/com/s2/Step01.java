package com.s2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Step01 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 方式1：用 new Thread() ===");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("线程执行中，线程名:" + Thread.currentThread().getName());
                Sleeper(5);
                System.out.println("线程任务完成");
            }, "t" + i).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== 方式2：用 ExecutorService ===");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(
                    () -> {
                        System.out.println("线程任务->" + taskId + "线程:" + Thread.currentThread().getName() + "执行");
                        Sleeper(5);
                        System.out.println("线程任务" + taskId + "完成");
                    });
        }

        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("等待超时，准备强制终止...");
            executor.shutdownNow();
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                System.err.println("线程池强制关闭失败，请检查是否有死循环或阻塞IO！");
            }
        }
        System.out.println("所有任务执行完毕");

    }

    public static void Sleeper(int i) {
        try {
            Thread.sleep(i * 100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }

    }
}
