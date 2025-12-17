package com.k4;

public class Solution29 {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double max = Integer.MIN_VALUE;
        double sum = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            while (i - j + 1 > k) {
                int num = nums[j];
                sum -= num;
                j++;
            }

            if (i - j + 1 == k) {
                max = Math.max(max, sum / k);
            }
        }
        return max;
    }
}
