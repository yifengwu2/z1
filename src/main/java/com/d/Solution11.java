package com.d;

public class Solution11 {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] avgs = new int[n];

        int left = 0;

        long sum = 0;
        for (int right = 0; right < n; right++) {
            if (right - k < 0 || right + k >= n) {
                avgs[right] = -1;
            }
            sum += nums[right];

            if (right - left + 1 > k * 2 + 1) {
                sum -= nums[left];
                left++;

            }

            if (right - left + 1 == 2 * k + 1) {
                int avg = (int) sum / (right - left + 1);
                avgs[right - k] = avg;
            }

        }
        return avgs;
    }
}
