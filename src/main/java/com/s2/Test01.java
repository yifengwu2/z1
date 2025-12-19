package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

/// price-system-core —— 轻量级价格服务治理骨架

/**
 * 获取价格的接口
 */
interface PriceService {
    String getPrice(String sku);
}

/**
 * 根据不同商品给出不同价格
 */
interface PriceCalculate {
    String priceCalculate(String sku);
}

class DefaultPriceCalculate implements PriceCalculate {
    @Override
    public String priceCalculate(String sku) {
        if (sku.trim().isEmpty()) {
            throw new IllegalArgumentException("sku为空");
        }
        return switch (sku) {
            case "VIP_SKU" -> "¥80.00";
            case "FLASH_SKU" -> "¥59.00";
            case "COM_SKU" -> "¥75.00";
            default -> "¥89.00";
        };
    }
}

@Slf4j(topic = "Test01")
public class Test01 {
    public static void main(String[] args) {
        PriceSystem system = PriceSystem.getInstance();
//        System.out.println(instance.getPriceWithRetry());
//        system.getPrice("A123");
//        log.info("价格为：{}", system.getPrice("ABC123"));
        log.info("价格为：{}", system.getPrice("FLASH_SKU"));
    }
}

/**
 * 单例模式：PriceService 系统总控台（双重检查锁优化）
 * 线程安全  延迟加载  全局唯一
 */
@Slf4j(topic = "PriceSystem")
class PriceSystem {
    //缓存
    private final Map<String, String> map = new HashMap<>();
    private final AtomicInteger callCount;
    //获取价格的接口
    private final PriceService priceService;
    //代理
    private final PriceService proxy;
    //重试器
    private final GenericRetryDecorator<String> retryDecorator;
    //测试，使商品能在测试中传参
    private final Function<String, String> getPriceFuction;
    //各个商品有自己的价格（策略）
    private final PriceCalculate priceCalculate;
    //单例
    private static volatile PriceSystem instance;

    private PriceSystem() {
        log.info("正在初始化:PriceSystem单例...");
        this.callCount = new AtomicInteger(0);

        this.priceCalculate = new DefaultPriceCalculate();
        //(sku -> {
//            if ("VIP_SKU".equals(sku)) return "¥80.00";
//            if ("FLASH_SKU".equals(sku)) return "¥59.00";
//            return "¥89.00";
//        });

        //创建原始业务服务
        this.priceService = s -> {
            //模拟网络超时
            if (callCount.incrementAndGet() <= 2) {
                throw new RuntimeException("Network timeout: " + s);
            }
            return priceCalculate.priceCalculate(s);
        };

        //创建代理（日志）
        this.proxy = ProxyUtil.createLoggingProxy(priceService);

        //加重试装饰（固定5次）
        this.retryDecorator = new GenericRetryDecorator<>(
                () -> proxy.getPrice("sku"), new FixedCountPolicy(5)
        );
        this.getPriceFuction = (sku -> new GenericRetryDecorator<>(
                () -> proxy.getPrice(sku), new FixedCountPolicy(5)).get()
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

    //提供对外访问方法(固定的)
    public String getPriceWithRetry() {
        return retryDecorator.get();
    }

    //提供Function调用(加缓存)
    public String getPrice(String sku) {
        if (map.containsKey(sku)) {
            return map.get(sku);
        }
        String price = getPriceFuction.apply(sku);
        map.put(sku, price);
        return price;
    }

    //加缓存
//    public String getCache(String sku) {
//        if (map.containsKey(sku)) {
//            return map.get(sku);
//        }
//        String price = getPrice(sku);
//        map.put(sku, price);
//        return price;
//    }

    //获取次数
    public int getCallCount() {
        return callCount.get();
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
        // 第1次重试前等 100ms，第2次等 200ms，第3次等 400ms...（2^n × 100）
        return (long) (Math.pow(2, attempt) * 100);
    }
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
@Slf4j(topic = "LoggerHandler")
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


