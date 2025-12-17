package com.b;

public class Solution15 {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;
        int countWeeks = 1;
        int k = 1;
        int sum = 0;

        while (countWeeks <= weeks) { //✅周循环
            for (int i = k; i < k + 7; i++) {
                sum += i;
                i++;
            }
            k++;
            countWeeks++;
        }
        //不是完整周
        for (int i = k; i < days + k; i++) {
            sum += i;
        }

        return sum;
    }
}
