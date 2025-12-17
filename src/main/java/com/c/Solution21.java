package com.c;

public class Solution21 {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int costTime = neededTime[0];
        int total = 0;

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                total += Math.min(costTime, neededTime[i]);
                costTime = Math.max(costTime, neededTime[i]);
            } else {
                costTime = neededTime[i];
            }
        }
        return total;
    }

}
