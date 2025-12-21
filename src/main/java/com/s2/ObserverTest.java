package com.s2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObserverTest {
    public static void main(String[] args) {

    }
}

interface Observer {
    void observer(OrderShippedEvent msg);
}

@Getter
@Slf4j
@Setter
@ToString
class OrderShippedEvent {
    private final String orderId;
    private final String userId;
    private final LocalDateTime shippedAt;
    private final String warehouseCode;

    public OrderShippedEvent(String orderId, String userId, LocalDateTime shippedAt, String warehouseCode) {
        this.orderId = orderId;
        this.userId = userId;
        this.shippedAt = shippedAt;
        this.warehouseCode = warehouseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderShippedEvent that = (OrderShippedEvent) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId) && Objects.equals(shippedAt, that.shippedAt) && Objects.equals(warehouseCode, that.warehouseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, shippedAt, warehouseCode);
    }
}

@Slf4j(topic = "User")
class User implements Observer {
    @Override
    public void observer(OrderShippedEvent event) {
        log.debug("用户 {} 的订单 {} 已从仓库 {} 发出，时间：{}",event.getUserId(),
                event.getOrderId(),
                event.getShippedAt(),event.getWarehouseCode()
        );
    }
}



@Slf4j(topic = "Order")
class Order {
    private String status;
    private final List<Observer> observers = new ArrayList<>();

    public Order() {
        this.status="NotShipped";
    }

    public void setStatus(String status) {
        this.status = status;
        if (Objects.equals(status, "Shipped")) {
            OrderShippedEvent event = new OrderShippedEvent("ORD-1001", "U999", LocalDateTime.now(), "WH-BJ");
            for (Observer observer : observers) {
                try {
                    observer.observer(event);
                } catch (IllegalArgumentException e) {
                    log.debug("{}", e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "status='" + status + '\'' +
                '}';
    }
}
