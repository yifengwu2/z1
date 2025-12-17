package com.b;

public class Solution14 {

    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;
        int countWeeks = 1;
        int k = 1;
        int sum = 0;

        while (countWeeks <= n / 7) { //✅周循环
            if ((n - days) / 7 > 1) {
                for (int i = k; i < k + 7; ) {
                    sum += i;
                    i++;
                }
            }
            k++;
            countWeeks++;
        }
        //不是完整周
        for (int i = k; i < days+k; ) {
            sum += i;
            i++;
        }

        return sum;
    }
}
