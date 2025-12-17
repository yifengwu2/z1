package com.k5;

public class Solution03 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        if (n == 1) return nums[0];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int maxSum = 0;
        int minPrefix = s[0];
        for (int i = 0; i < n; i++) {
            //维护历史最小值
            minPrefix = Math.min(minPrefix, s[i]);
            int res = s[i + 1] - minPrefix;
            maxSum = Math.max(maxSum, res);
        }
        return maxSum;
    }
}
