package com.k7;

public class Solution51 {
    public int smallestBalancedIndex(int[] nums) {
        int n = nums.length;
        long[] s1 = new long[n];
        s1[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            s1[i + 1] = s1[i] + nums[i];
        }
        long[] s2 = new long[n];
        s2[n - 1] = 1;
        for (int i = n - 1; i > 0; i--) {
            s2[i - 1] = nums[i] * s2[i];
        }
        int min = Integer.MAX_VALUE;
        long MAX_SUM = 0;
        for (int x : nums) MAX_SUM += x;
        for (int i = 0; i < n; i++) {
            if (s2[i] <= 0 || s2[i] > MAX_SUM) continue;
            if (s1[i] == s2[i]) {
                min = Math.min(min, i);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
