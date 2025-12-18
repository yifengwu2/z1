package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 异常驱动的、轻量级重试装饰器
 */

//interface CoffeeMarker {
//    String makeCoffee();
//
//}


interface PriceService {
    String getPrice(String sku);
}

@Slf4j(topic = "Test01")
public class Test01 {
    public static void main(String[] args) {
        AtomicInteger callCount = new AtomicInteger(0);
        PriceService priceService = s -> {
            if (callCount.incrementAndGet() <= 2) {
                throw new RuntimeException("Network timeout: " + s);
            }
            return "¥89.00";
        };
        GenericRetryDecorator<String> retryDecorator = new GenericRetryDecorator<>(
                () -> priceService.getPrice("SKU"), new FixedCountPolicy(5)
        );
        log.debug(retryDecorator.get());
    }
}

@Slf4j(topic = "FixedCountPolicy")
class FixedCountPolicy implements RetryPolicy {
    private int maxAttempt;

    public FixedCountPolicy(int maxAttempt) {
        this.maxAttempt = maxAttempt;
    }

    @Override
    public boolean shouldRetry(int attempt, Throwable failure) {
        return attempt < maxAttempt;
    }

    //delayMs(0) 表示第 1 次重试前等待时间；
    @Override
    public long delayMs(int attempt) {
        return 0;
    }
}

/**
 * 这次失败后，还该重试吗？
 * 如果重试，该等多久再按按钮？
 */
interface RetryPolicy {
    boolean shouldRetry(int attempt, Throwable failure);

    long delayMs(int attempt);
}


/**
 * 重试装饰器
 */
@Slf4j(topic = "GenericRetryDecorator")
class GenericRetryDecorator<T> implements Supplier<T> {
    private final Supplier<T> delegate;
    private final RetryPolicy policy;

    public GenericRetryDecorator(Supplier<T> delegate, RetryPolicy policy) {
        this.delegate = delegate;
        this.policy = policy;
    }

    @Override
    public T get() {
        int attempt = 0;
        while (true) {
            try {
                return delegate.get();
            } catch (RuntimeException e) {
                log.debug("第 {} 次尝试失败：{}", attempt + 1, e.getMessage());
                if (!policy.shouldRetry(attempt, e)) {
                    throw e;
                }
                try {
                    Thread.sleep(policy.delayMs(attempt));
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }
                attempt++;
            }
        }
    }
}

