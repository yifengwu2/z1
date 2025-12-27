package com.s2;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChefPipelineDemo {
    // 厨师 = 单线程池（模拟“1 个厨师”）
    private static final ExecutorService chef = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "Chef-Thread");
        t.setDaemon(false);
        t.start();
        return t;
    });

    public static void main(String[] args) {
        System.out.println("=== 同步做法：厨师傻等 ===");
        Instant startSync = Instant.now();
        syncCook();
        //几分
        Duration duration = Duration.between(startSync, Instant.now());
        System.out.printf("同步总耗时：%d 分 %d 秒%n%n", duration.toMillis(), duration.getSeconds() % 60);


    }

    //同步
    static void syncCook() {
        step("煮面", 120000); //2分钟
        step("煎蛋", 60000); //1分钟
        step("切葱", 10000); //10秒
    }

    //异步
    static void asyncCook() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                    step("煮面", 120000);
                    return null;
                }, chef)
                //这个是串行，让厨师线程去等待规定时间后，结束后返回值传给下一个继续执行值
                //thenCompose 完成并返回值后，fn 才被调用
                //.thenRunAsync 不接收前值，且不保证顺序
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    step("煎蛋", 60_000);
                    //return不会返回信息到控制台，只是给下一个任务接收
                    return null;
                }, chef))
                .thenAcceptAsync(v -> {
                    step("切葱", 10000);
                },chef);
    }


    static void step(String name, long ms) {
        String time = LocalTime.now().toString().substring(0, 8);
        System.out.printf("[%s] 开始 %s (预计 %d 秒)", time, name, ms / 1000);
        try {
            Thread.sleep(ms);// 模拟真实耗时
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String end = LocalTime.now().toString().substring(0, 8);
        System.out.printf("[%s] %s 完成 %n", name, end);


    }


}
