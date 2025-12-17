package com.k4;

public class Solution25 {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double max = Integer.MIN_VALUE;
        double sum = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            while (i - j + 1 == k) {
                max = Math.max(max, sum / k);
                int num = nums[j];
                if (num > 0) {
                    sum -= num;
                } else {
                    int i1 = -num;
                    sum += i1;
                }
                j++;
            }
        }
        return max;
    }
}
