package com.k6;

public class Solution10 {
    public int bestClosingTime(String s) {
        int n = s.length();
        int total = 0;
        for (char c : s.toCharArray()) {
            if (c == 'Y') {
                total++;
            }
        }
        int minCost = total;
        int bestJ = 0;

        int leftN = 0;
        int rightY = total;
        //[i,n]关门区间
        //[0，i)开门区间
        for (int i = 1; i <= n; i++) {
            char prev = s.charAt(i - 1);
            if (prev == 'Y') {
                rightY--;
            } else if (prev == 'N') {
                leftN++;
            }
            int cost = rightY + leftN;
            if (cost < minCost) {
                minCost = cost;
                bestJ = i;
            }
        }
        return bestJ;

    }
}
