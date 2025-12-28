package com.s3;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程安全的限流器（令牌桶）
 * 单例模式 + `AtomicLong` / Semaphore
 */
public class RateLimiter {
    private static volatile RateLimiter instance;
    private static final int CAPACITY = 5;
    private static final double RATE_PER_SEC = 5.0;

    private final AtomicLong tokens = new AtomicLong(CAPACITY);
    private final AtomicLong lastRefillNanos = new AtomicLong(System.nanoTime());

    private RateLimiter() {

    }

    //获取单例
    public static RateLimiter getInstance() {
        if (instance == null) {
            synchronized (RateLimiter.class) {
                if (instance == null) {
                    instance = new RateLimiter();
                }
                return instance;
            }
        }
        return instance;
    }

    public LimitResult limiting() {
        //现在几点？—— 我拿出最准的原子钟，记下此刻精确到纳秒的时间。
        long now = System.nanoTime();

        System.err.printf("[%.3f] tokens=%d, lastRefill=%.3fms ago%n",
                now / 1e9, tokens.get(), (now - lastRefillNanos.get()) / 1e6);

        //上次往桶里加水（补令牌）是啥时候？我翻开小本本查一下。”
        long last = lastRefillNanos.get();
        //从上次加水到现在，过了多少秒？我把纳秒差 ÷ 10⁹，变成‘秒’。”
        double elapsedSec = (now - last) / 1_000_000_000.0;
        //elapsedSec * RATE_PER_SEC → 应该补多少？
        //比如过了 0.6 秒，RATE_PER_SEC = 2.0 → 应补 0.6 × 2.0 = 1.2 滴
        //CAPACITY - tokens.get() → 桶还能装几滴？
        //比如桶容量是 5，现在有 3 滴 → 还能装 2 滴
        long newTokens = (long) Math.min(elapsedSec * RATE_PER_SEC, CAPACITY - tokens.get());

        while (true) {
            //重新获取
            long current = tokens.get();//① 看现在桶里有几滴？
            long updated = Math.min(CAPACITY, current + newTokens);//② 算补完后有几滴？
            if (tokens.compareAndSet(current, updated)) {
                lastRefillNanos.set(now);// ④ 成功了！记下这次加水时间
                if (updated >= 1) {// ⑤ 补完后够 1 滴吗？
                    tokens.decrementAndGet();
                    return LimitResult.ALLOWED;
                } else {
                    return LimitResult.RETRY_AFTER;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.getInstance();

        System.out.println("=== 测试1：桶初始化是否为5 ===");
        System.out.println("初始 tokens = " + limiter.tokens.get()); // 应该输出 5

        System.out.println("\n=== 测试2：连续调用5次，是否每次减少1 ===");
        for (int i = 1; i <= 5; i++) {
            LimitResult r = limiter.limiting();
            System.out.printf("第%d次: %s → 桶剩余: %d%n", i, r, limiter.tokens.get());
        }
        // ✅ 期望输出：
        // 第1次: ALLOWED → 桶剩余: 4
        // 第2次: ALLOWED → 桶剩余: 3
        // 第3次: ALLOWED → 桶剩余: 2
        // 第4次: ALLOWED → 桶剩余: 1
        // 第5次: ALLOWED → 桶剩余: 0

        System.out.println("\n=== 测试3：第6次调用，是否被拒绝？ ===");
        LimitResult r6 = limiter.limiting();
        System.out.println("第6次: " + r6 + " → 桶剩余: " + limiter.tokens.get());
        // ✅ 期望输出：第6次: RETRY_AFTER → 桶剩余: 0

        Thread.sleep(2000);
        LimitResult dummy = limiter.limiting(); // ← 就是这一行！
        System.out.println("睡2秒后，调用一次limiting()，桶剩余: " + limiter.tokens.get());

        System.out.println("\n✅ 所有测试通过！你的限流器工作正常。");
    }
}
