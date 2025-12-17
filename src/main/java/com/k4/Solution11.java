package com.k4;

public class Solution11 {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        int max = 0;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
            if (nums[i] - min > 0) {
                max = Math.max(max, nums[i] - min);
            }
        }
        return max == 0 ? -1 : max;
    }
}
