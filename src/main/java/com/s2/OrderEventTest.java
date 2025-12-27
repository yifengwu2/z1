package com.s2;

import java.util.ArrayList;
import java.util.List;

// OrderEvent.java —— 封装“发生了什么”
class OrderEvent {
    private final String orderId;
    private final String oldStatus;
    private final String newStatus;
    private final long timestamp;

    public OrderEvent(String orderId, String oldStatus, String newStatus) {
        this.orderId = orderId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.timestamp = System.currentTimeMillis();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("OrderEvent{orderId='%s' ,%s -> %s}", orderId, oldStatus, newStatus);
    }
}

//实现类，被通知者
interface OrderListener {
    void onStatusChanged(OrderEvent event);
}

//订单类，业务变更时发通知（发送者）
class Order {
    private String orderId;
    private String status = "CREATED";
    private final List<OrderListener> listeners = new ArrayList<>();

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OrderListener listener) {
        listeners.remove(listener);
    }

    //通知
    public void updateOrder(String newStatus) {
        String oldStatus = this.status;
        this.status = newStatus;

        OrderEvent event = new OrderEvent(orderId, oldStatus, newStatus);
        for (OrderListener listener : listeners) {
            listener.onStatusChanged(event);
        }
    }
}

class SmsNotificationListener implements OrderListener {
    @Override
    public void onStatusChanged(OrderEvent event) {
        System.out.println("📱 SMS sent: Order " + event.getOrderId()
                + " status changed to " + event.getNewStatus());
    }
}

class EmailNotificationListener implements OrderListener {
    @Override
    public void onStatusChanged(OrderEvent event) {
        System.out.println("📧 Email sent: " + event.getOrderId()
                + " is now " + event.getNewStatus() + " (at " + event.getTimestamp() + ")");
    }
}

class InventoryUpdateListener implements OrderListener {
    @Override
    public void onStatusChanged(OrderEvent event) {
        if ("DELIVERED".equals(event.getNewStatus())) {
            System.out.println("📦 Inventory updated: Order " + event.getOrderId() + " delivered.");
        }
    }
}

public class OrderEventTest {
    public static void main(String[] args) {
        Order order = new Order("ORD-2025-001");

        order.addListener(new SmsNotificationListener());
        order.addListener(new EmailNotificationListener());
        order.addListener(new InventoryUpdateListener());

        System.out.println("→ Setting status to PAID...");
        order.updateOrder("PAID");

        System.out.println("\n→ Setting status to SHIPPED...");
        order.updateOrder("SHIPPED");

        System.out.println("\n→ Setting status to DELIVERED...");
        order.updateOrder("DELIVERED");

        System.out.println("\n→ Removing SMS listener...");
        order.removeListener(new SmsNotificationListener()); // 注意：实际中建议用引用变量移除

        System.out.println("\n→ Setting status to COMPLETED...");
        order.updateOrder("COMPLETED"); // SMS 不会收到，其他会
    }

}


