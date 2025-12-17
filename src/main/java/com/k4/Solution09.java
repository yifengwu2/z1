package com.k4;

public class Solution09 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxCount = 0;
        int min = prices[0];
        for (int i = 0; i < n; i++) {
            int price = prices[i];
            min = Math.min(min, price);
            if (prices[i] - min > 0) {
                maxCount = Math.max(maxCount, prices[i] - min);
            }
        }
        return maxCount;
    }
}
