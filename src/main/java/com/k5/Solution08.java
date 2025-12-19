package com.k5;

import java.util.Map;

public class Solution08 {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) prices[i] * strategy[i];
        }
        long maxCount = sum;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (i - j + 1 > k) {
                j++;
            }
            if (i - j + 1 == k) {
                long max = sum;
                for (int l = j; l <= i; l++) {
                    if (l - j < k / 2) {
                        if (strategy[l] == -1) {
                            max += prices[l];
                        } else if (strategy[l] == 1) {
                            max -= prices[l];
                        }
                    } else {
                        if (l - j >= k / 2) {
                            if (strategy[l] == -1) {
                                max += 2L * prices[l];
                            } else if (strategy[l] == 0) {
                                max += prices[l];
                            }
                        }
                    }
                }
                maxCount = Math.max(maxCount, max);
            }
        }
        return maxCount;
    }
}
