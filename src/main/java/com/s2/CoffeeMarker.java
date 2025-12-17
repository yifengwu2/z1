package com.s2;

public interface CoffeeMarker {
    String makeCoffee();
}

/**
 * 重试装饰器
 */
class RetryDecorator implements CoffeeMarker {
    private CoffeeMarker coffeeMarker;
    private final int maxRetries;

    public RetryDecorator(CoffeeMarker coffeeMarker, int maxRetries) {
        this.coffeeMarker = coffeeMarker;
        this.maxRetries = maxRetries;
    }

    @Override
    public String makeCoffee() {
        try {
            return coffeeMarker.makeCoffee();
        } catch (CoffeeMachineException e) {
            for (int i = 0; i <= maxRetries; i++) {
                try {
                    return coffeeMarker.makeCoffee();
                } catch (CoffeeMachineException ex) {
                    if (i == maxRetries) {
                        throw ex;
                    }
                    System.out.println("正在进行尝试第" + i + "次");
                    System.out.println(ex.getMessage());

                }
            }
        }
        throw new RuntimeException("unreachable");
    }
}
