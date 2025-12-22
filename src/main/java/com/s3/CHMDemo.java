package com.s3;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CHMDemo {
    public static void main(String[] args) {
        System.out.println("=== ConcurrentHashMap 学习实战 ===\n");
        method4();
        System.out.println("掌握 CHM 最核心的生产级用法。");
    }

    public static void method1() {
        System.out.println("【Step 1】基础安全性验证");
        System.out.println("→ 创建 100 个线程，同时向 map 中 put 1000 次 key='A'");
        Map<String, Integer> map = new HashMap<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int i1 = i;
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    map.put("A", i1);
                }
            });
            threads.add(t);
        }
        try {
            threads.forEach(Thread::start);
            threads.forEach((t) -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("HashMap 测试未崩溃？（小概率）但实际极不稳定");
        } catch (Exception e) {
            System.out.println("HashMap崩溃了" + e.getClass().getSimpleName());
        }
        //测试 ConcurrentHashMap
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        threads.clear();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    chm.put("A", j);
                }
            }));
        }
        threads.forEach(Thread::start);
        threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("CHM 成功完成 100×1000 次并发 put，最终值：" + chm.get("A"));
        System.out.println();
    }

    public static void method2() {
        System.out.println("【Step 2】computeIfAbsent()：懒加载+线程安全");
        System.out.println("→ 模拟 10 个线程同时查询 key='book-1001'，只应加载 1 次！");
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        AtomicInteger dbLoadCount = new AtomicInteger(0);
        Runnable loadTask = () -> {
            String result = cache.computeIfAbsent("book-1001", key -> {
                System.out.println("线程" + Thread.currentThread().getName() + "正在加载book1");
                dbLoadCount.getAndIncrement();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                return "《Java并发编程实战》";
            });
            System.out.println("线程 " + Thread.currentThread().getName() + " 获取到：" + result);
        };
        System.out.println("→ 数据库实际加载次数：" + dbLoadCount.get() + "（应为 1）");
        System.out.println();
    }

    public static void method3() {
        System.out.println("【Step 3】merge()：安全计数与扣减");
        System.out.println("→ 库存初始为 5，10 个线程各尝试扣减 1，最终库存应 ≥ 0");
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
        inventory.put("book-1", 5);
        Runnable orderTask = () -> {
            boolean success = inventory.merge("book-1", -1, Integer::sum) >= 0;
            if (success) {
                System.out.println("Thread" + Thread.currentThread().getName() + " 下单成功，剩余库存：" + inventory.get("book-1"));
            } else {
                System.out.println("Thread" + Thread.currentThread().getName() + " 下单失败：库存不足");
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(orderTask, "ORDER-" + i));
        }
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });

        System.out.println("→ 最终库存：" + inventory.get("book-1") + "（应在 0~5 之间）");
        System.out.println();

    }

    public static void method4() {
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
        inventory.put("book-01", 5);
        Runnable task = () -> {
            Integer compute = inventory.compute("book-01", (key, oldValue) -> {
                if (oldValue == null || oldValue <= 0) {
                    return oldValue;
                }
                return oldValue - 1;
            });
            boolean success = compute != null && compute >= 0;
            if (success) {
                System.out.println("Thread " + Thread.currentThread().getName() + " 下单成功，剩余库存：" + compute);
            }else {
                System.out.println("Thread " + Thread.currentThread().getName() + " 下单失败：库存不足");
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(task, "ORDER-" + i));
        }

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try { t.join(); } catch (InterruptedException e) {}
        });

        // ✅ 修复：统一用 "book-1" 查
        Integer finalStock = inventory.get("book-1");
        System.out.println("→ 最终库存：" + finalStock + "（应在 0~5 之间）");
        System.out.println();

    }
}
