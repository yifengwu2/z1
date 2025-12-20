package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Slf4j(topic = "TestTimer")
public class TestTimer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
//        pool.schedule(() -> {
//            log.debug("task1");
//            try {
//                int i = 1 / 0;
//            } catch (Exception e) {
//                log.error("error",e);
//            }
//        }, 1, TimeUnit.SECONDS);
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            log.debug("task1");
            int i = 1 / 0;
            return true;
        });
        log.debug("result{}", f.get());
    }

    public static void method3() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start...");
//        pool.scheduleAtFixedRate(() -> {
//            log.debug("running...");
//            sleep(2);
//        }, 1, 1, TimeUnit.SECONDS);

        pool.scheduleWithFixedDelay(() -> {
            log.debug("running...");
            sleep(2);
        }, 1, 1, TimeUnit.SECONDS);

    }

    public static void method2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(() -> {
            log.debug("task1");
            int i = 1 / 0;
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);

    }

    public static void method1() {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };
        log.debug("start...");
        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
