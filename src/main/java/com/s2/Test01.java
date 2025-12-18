package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 异常驱动的、轻量级重试装饰器
 */

/**
 * 获取价格的接口
 */
interface PriceService {
    String getPrice(String sku);
}

@Slf4j(topic = "Test01")
public class Test01 {
    public static void main(String[] args) {
        PriceSystem instance = PriceSystem.getInstance();
        System.out.println(instance.getPriceWithRetry());
    }
}

/**
 * 单例模式：PriceService 系统总控台（双重检查锁优化）
 * 线程安全  延迟加载  全局唯一
 */
@Slf4j(topic = "PriceSystem")
class PriceSystem {
    private final AtomicInteger callCount;
    private final PriceService priceService;
    private final PriceService proxy;
    private final GenericRetryDecorator<String> retryDecorator;

    private static volatile PriceSystem instance;

    private PriceSystem() {
        log.info("正在初始化:PriceSystem单例...");
        this.callCount = new AtomicInteger(0);

        //创建原始业务服务
        this.priceService = s -> {
            //模拟网络超时
            if (callCount.incrementAndGet() <= 2) {
                throw new RuntimeException("Network timeout: " + s);
            }
            return "¥89.00";
        };
        //创建代理（日志）
        this.proxy = ProxyUtil.createLoggingProxy(priceService);

        //加重试装饰（固定5次）
        this.retryDecorator = new GenericRetryDecorator<>(
                () -> proxy.getPrice("SKU"), new FixedCountPolicy(5)
        );
    }

    public static PriceSystem getInstance() {
        if (instance == null) {
            synchronized (PriceSystem.class) {
                if (instance == null) {
                    instance = new PriceSystem();
                    return instance;
                }
            }
        }
        return instance;
    }

    //提供对外访问方法
    public String getPriceWithRetry() {
        return retryDecorator.get();
    }

    //获取次数
    public int getCallCount() {
        return callCount.get();
    }
}

/**
 * 策略实现类
 */
@Slf4j(topic = "FixedCountPolicy")
class FixedCountPolicy implements RetryPolicy {
    private final int maxAttempt;

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
 * 策略模式
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

/**
 * 代理模式
 */
@Slf4j
class LoggerHandler implements InvocationHandler {
    private final PriceService target;

    public LoggerHandler(PriceService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        log.debug("动态代理收到的请求:{}.{}({})", target.getClass().getSimpleName(), methodName, Arrays.toString(args));
        try {
            Object result = method.invoke(target, args);
            log.debug("动态代理收到结果：{}.{} → {}", target.getClass().getSimpleName(), methodName, result);
            return result;
        } catch (Exception e) {
            log.error("动态代理捕获异常：{}.{} → {}",
                    target.getClass().getSimpleName(), methodName, e.getCause().getMessage());
            throw e.getCause(); // 抛出原始异常，不包一层
        }
    }
}

class ProxyUtil {
    public static PriceService createLoggingProxy(PriceService target) {
        return (PriceService) Proxy.newProxyInstance(PriceService.class.getClassLoader(),
                new Class[]{PriceService.class},
                new LoggerHandler(target)
        );
    }

}


