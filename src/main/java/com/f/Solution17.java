package com.f;

import java.util.Arrays;

public class Solution17 {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return FairPairs(nums, upper) - FairPairs(nums, lower - 1);
    }

    private long FairPairs(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        long count = 0;
        while (left < right) {
            long sum = nums[left] + nums[right];
            if (sum <= target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
