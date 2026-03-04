package com.k7;

public class Solution40 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        int dis = nums[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(nums[i], nums[i] + sum);
            dis = Math.max(dis, sum);
        }
        return dis;
    }
}
