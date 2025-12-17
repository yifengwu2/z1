package com.f;

public class Solution01 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return solove(nums, goal) - solove(nums, goal - 1);
    }

    private int solove(int[] nums, int k) {
        if (k < 0) return 0;
        int sum = 0;
        int left = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > k) {
                int num1 = nums[left];
                sum -= num1;
                left++;
            }
            count += right-left+1;
        }
        return count;
    }
}
