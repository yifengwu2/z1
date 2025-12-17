package com.k5;

public class Solution04 {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int minPrefix = s[0];
        int maxPrevfix = s[0];
        int maxSum = nums[0];
        for (int i = 0; i < n; i++) {
            minPrefix = Math.min(minPrefix, s[i]);
            maxPrevfix = Math.max(maxPrevfix, s[i]);
            maxSum = Math.max(maxSum, Math.abs(s[i + 1] - minPrefix));
            maxSum = Math.max(maxSum, Math.abs(s[i + 1] - maxPrevfix));
        }
        return maxSum;

    }
}
