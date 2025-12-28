package com.s3;

import java.util.concurrent.atomic.AtomicLong;

public class Limiter {
    private final int CAPITAL_SIZE = 5;
    private final double RATE = 2.0;
    private final AtomicLong tokens = new AtomicLong(CAPITAL_SIZE);
    private final AtomicLong lastRefillNanos = new AtomicLong(System.nanoTime());


    public LimitResult limit() {
        while (true) {
            //纳秒，这次时间
            long now = System.nanoTime();
            //上次时间
            long lastTime = lastRefillNanos.get();
            //时间间隔
            long duration = now - lastTime;
            //当前容量
            long current = tokens.get();
            //转化为秒
            double dur = duration / 1000_000_000.0;
            //剩余容量，要加的值
            long newTokens = (long) Math.min(CAPITAL_SIZE - current, RATE * dur);
            //当前桶里还有多少
            long update = current + newTokens;
            //判断是否更新成功
            //他到这里都会去看tokens里面的是否是预期值吧
            //就是「原子地读取 tokens 当前真实值」，
            // 然后立刻和你传入的 current 比较 —— 如果相等，才写入 update；否则什么也不做，直接返回 false。
            if (tokens.compareAndSet(current, update)) {
                lastRefillNanos.set(now);
                if (update >= 1) {
                    tokens.decrementAndGet();
                    return LimitResult.ALLOWED;
                }
                return LimitResult.RETRY_AFTER;
            }
        }

    }
}
