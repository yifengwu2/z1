package com.e;

public class Solution19 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        int left = 0;
        int sum = 1;
        if (k <= 1) return 0;
        for (int right = 0; right < n; right++) {
            int num = nums[right];
            sum *= num;
            while (sum >= k) {
                int num1 = nums[left];
                sum /= num1;
                left++;
            }
            count += right - left + 1;

        }
        return count;
    }
}
