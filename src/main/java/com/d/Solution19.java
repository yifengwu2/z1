package com.d;

import java.util.Arrays;

public class Solution19 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (String s : strs) {
            int count1 = 0;
            int count0 = 0;
            for (int c : s.toCharArray()) {
                if (c == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            for (int i = m; i >= count0; i--) {
                for (int j = n; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count0][j - count1]+1);
                }
            }
        }
        return dp[m][n];
    }
}
