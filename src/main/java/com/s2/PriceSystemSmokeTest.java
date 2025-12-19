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

//supplier提供者 无中生有 ()->结果
//function 函数 一个参数一个结果 (参数)->结果,BigFunction(参数1，参数2)->结果
//consumer 消费者 一个参数没结果 (参数)->void，BiConsumer(参数1，参数2)->void

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
    String priceCalculate(String sku, PriceStage stage);
}

/**
 * 不同商品策略
 */
interface PriceStage {
    String getPriceStage();
}

// VIP 会员价策略
class vipSku implements PriceStage {
    @Override
    public String getPriceStage() {
        return "¥80.00 (VIP专享)";
    }
}

// 秒杀价策略
class flashSku implements PriceStage {
    @Override
    public String getPriceStage() {
        return "¥59.00 (限时秒杀)";
    }
}

// 常规价策略
class comSku implements PriceStage {
    @Override
    public String getPriceStage() {
        return "¥75.00 (标准售价)";
    }
}

// 兜底策略
class FallbackPricingStrategy implements PriceStage {
    @Override
    public String getPriceStage() {
        return "¥89.00 (暂无特殊报价)";
    }
}

class DefaultPriceCalculate implements PriceCalculate {
    @Override
    public String priceCalculate(String sku, PriceStage stage) {
        if (sku.trim().isEmpty()) {
            throw new IllegalArgumentException("sku为空");
        }
        return stage.getPriceStage();
//        return switch (sku) {
//            case "VIP_SKU" -> "¥80.00";
//            case "FLASH_SKU" -> "¥59.00";
//            case "COM_SKU" -> "¥75.00";
//            default -> "¥89.00";
//        };
    }
}

/**
 * 单例模式：PriceService 系统总控台（双重检查锁优化）
 * 线程安全  延迟加载  全局唯一
 */
@Slf4j(topic = "PriceSystem")
class PriceSystem {
    //不同商品策略接口
    private PriceStage priceStage;
    //存不同商品对应价格
    private static final Map<String, PriceStage> stageMap = new HashMap<>();
    //缓存
    private final Map<String, String> map = new HashMap<>();
    //模拟网络尝试次数
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

    static {
        stageMap.put("vip", new vipSku());
        stageMap.put("flash", new flashSku());
        stageMap.put("com", new comSku());
    }

    private PriceSystem() {
        log.info("正在初始化:PriceSystem单例...");
        this.callCount = new AtomicInteger(0);

        this.priceCalculate = new DefaultPriceCalculate();
        //(sku -> {
//            if ("VIP_SKU".equals(sku)) return "¥80.00";
//            if ("FLASH_SKU".equals(sku)) return "¥59.00";
//            return "¥89.00";
//        });
        this.priceStage = new FallbackPricingStrategy();

        //创建原始业务服务
        this.priceService = s -> {
            //模拟网络超时
            if (callCount.incrementAndGet() <= 2) {
                throw new RuntimeException("Network timeout: " + s);
            }
            PriceStage stage = stageMap.getOrDefault(s, new FallbackPricingStrategy());
            return priceCalculate.priceCalculate(s, stage);
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

@Slf4j(topic = "PriceSystemSmokeTest")
public class PriceSystemSmokeTest {
    public static void main(String[] args) {
        PriceSystem system = PriceSystem.getInstance();
        // 场景化命名：模拟真实业务调用
        log.info("[SMOKE] 查询 VIP 商品价格 → {}", system.getPrice("vip"));
        log.info("[SMOKE] 查询 秒杀商品价格 → {}", system.getPrice("flash"));
        log.info("[SMOKE] 查询 常规商品价格 → {}", system.getPrice("com"));
        log.info("[SMOKE] 查询 未知SKU（兜底） → {}", system.getPrice("unknown"));

        //验证缓存生效（第二次查同一 SKU 不走重试）
        log.info(" [CACHE] 第二次查 'vip'（应命中缓存）→ {}", system.getPrice("vip"));


    }
}


