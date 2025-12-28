package com.f;

public class Solution25 {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = 0;
        long max = 0;
        for (int i = 0; i <= n - 2; i++) {
            s[i + 1] = s[i] + nums[i];
            max = Math.max(max, s[i + 1]);
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i == max) {
                k = i;
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = k - 1; i < n; i++) {
            min = Math.min(min, nums[i]);
        }
        return max - min;
    }
}
