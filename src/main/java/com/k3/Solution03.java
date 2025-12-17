package com.k3;

public class Solution03 {
    public long getDescentPeriods(int[] prices) {
//        int n = prices.length;
//        int i = 0;
//        int ans = 0;
//        int maxLen = 0;
//        while (i < n) {
//            int j = i + 1;
//
//            while (j < n && prices[j] - prices[j - 1] == 1) {
//                j++;
//            }
//            ans += Math.max(maxLen, j - i);
//
//            i = j;
//        }
//
//        return ans;

        int n = prices.length;
        long ans = 0;
        int i = 0;
        if (n == 1) return 1;

        while (i < n) {

            int j = i + 1;
            while (j < n && prices[j] - prices[j - 1] == -1) {
                j++;
            }
            long len = j - i;
            ans += len * (len + 1) / 2;

            i = j;
        }

        return ans;
    }
}
