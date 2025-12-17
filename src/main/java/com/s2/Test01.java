package com.s2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

interface CoffeeMarker {
    String makeCoffee();

}

interface PriceService {
    String getPrice(String sku);
}

public class Test01 {
    public static void main(String[] args) {
        AtomicInteger callCount = new AtomicInteger(0);
        PriceService priceService = s -> {
            if (callCount.incrementAndGet() <= 2) {
                throw new RuntimeException("Network timeout: " + s);
            }
            return "¥89.00";
        };
        RetryDecorator<String> retryDecorator = new RetryDecorator<>(
                () -> priceService.getPrice("SKU"), 5
        );
        System.out.println(retryDecorator.get());
    }
}

/**
 * 重试装饰器
 */
class RetryDecorator<T> implements Supplier<T> {
    private final Supplier<T> delegate;
    private final int maxRetries;

    public RetryDecorator(Supplier<T> delegate, int maxRetries) {
        this.delegate = delegate;
        this.maxRetries = maxRetries;
    }

    @Override
    public T get() {
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                return delegate.get();
            } catch (RuntimeException e) {
                if (attempt == maxRetries) {
                    throw e;
                }
                System.out.println("第 " + (attempt + 1) + " 次尝试失败：" + e.getMessage());
            }
        }
        throw new RuntimeException("unreachable");
    }
}
