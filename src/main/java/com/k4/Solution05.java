package com.k4;

public class Solution05 {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            int j = i;
            int k = i;
            while (j + 1 < n && nums[j + 1] - nums[j] > 0) {
                j++;
            }
            while (k + 1 < n && nums[k + 1] - nums[k] < 0) {
                k++;
            }
            maxLen = Math.max(maxLen, Math.max(k - i + 1, j - i + 1));

            i++;
        }
        return maxLen;
    }
}
