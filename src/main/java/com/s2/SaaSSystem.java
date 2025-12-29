package com.s2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 用户行为追踪监听器
 * 模拟一个 SaaS 系统：当用户发生关键行为（如 LOGIN, CLICK_BUTTON, PAGE_STAY_LONG），需通知多个监听器：
 * 🔐 风控服务（检查是否异常登录）
 * 📊 埋点服务（上报用户行为到大数据平台）
 * 💰 营销服务（触发优惠券发放）
 */
public class SaaSSystem {
    public static void main(String[] args) {

    }

}

//封装事件
class UserEvent {
    //用户Id
    private final String userId;
    //旧状态
    private final EventType oldStatus;
    //新状态
    private final EventType newStatus;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //发生时间
    private final String time;

    public UserEvent(String userId, EventType oldStatus, EventType newStatus) {
        this.userId = userId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.time = LocalDateTime.now().format(formatter);
    }

    public String getUserId() {
        return userId;
    }

    public EventType getOldStatus() {
        return oldStatus;
    }

    public EventType getNewStatus() {
        return newStatus;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("[%s] userId:[%s],%s -> %s ",
                time, userId, oldStatus, newStatus
        );
    }
}

//被监听者
interface UserListener {
    boolean listener(UserEvent event);
}

/**
 * 用户类
 */
enum UserState {
    NOLOGIN, LOGGED_IN, LOGGED_OUT, IDLE
}

class SUser {
    private String userId;
    private UserState status = UserState.NOLOGIN;
    private final List<UserListener> listeners = new ArrayList<>();
    private final static ExecutorService pool = Executors.newFixedThreadPool(3, r -> new Thread(r, "user-event-notifier-" + r.hashCode()));

    public SUser(String userId) {
        this.userId = userId;
    }

    //添加监听者
    public void addListener(UserListener listener) {
        listeners.add(listener);
    }

    //删除监听者
    public void removeListener(UserListener listener) {
        listeners.remove(listener);
    }

    //更新状态并通知
    public void updateStatus(EventType newStatus) {
        UserState oldStatus = this.status;
        if (newStatus == EventType.LOGIN) this.status = UserState.LOGGED_IN;
        if (newStatus == EventType.LOGOUT) this.status = UserState.LOGGED_OUT;
        UserEvent userEvent = new UserEvent(userId, null, newStatus);
        pool.submit(() -> {
            //通知
            for (UserListener listener : listeners) {
                boolean success = listener.listener(userEvent);
                if (!success) {
                    break;
                }
            }

        });
    }

    public static void shutdown() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(3, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * 营销监听器
 */
class MarketingListener implements UserListener {
    @Override
    public boolean listener(UserEvent event) {
        if (Objects.equals(event.getNewStatus(), EventType.CLICK_BUTTON)) {
            System.out.println(event);
            System.out.println("营销监听器正在监听...");
            System.out.println("触发优惠券发放...");
            return true;
        }
        return true;
    }
}

/**
 * 埋点服务监听器
 */
class BuriedServiceListener implements UserListener {
    @Override
    public boolean listener(UserEvent event) {
        System.out.println(event);
        System.out.println("埋点服务正在监听...");
        System.out.println("上报用户行为到大数据平台...");
        return true;
    }
}

/**
 * 风控监听器
 */
class RiskControlListener implements UserListener {
    @Override
    public boolean listener(UserEvent event) {
        System.out.println("风控服务正在监听...");
        System.out.println("检查是否异常登录...");
        return true;
    }
}





