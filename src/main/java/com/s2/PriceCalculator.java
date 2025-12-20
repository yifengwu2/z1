package com.s2;

import java.lang.reflect.Method;

public class PriceCalculator {
    @LogTime
    public double calculateVipPrice(double origin, int discount) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return origin * (1 - discount / 100.0);
    }

    public static void main(String[] args) throws Exception {
        PriceCalculator calc = new PriceCalculator();
        Method method = PriceCalculator.class.getMethod("calculateVipPrice", double.class, int.class);
        Object result = AnnotationUtils.invokeWithTimeLog(calc, method, 100.0, 20);
        System.out.println(result);



    }
}
