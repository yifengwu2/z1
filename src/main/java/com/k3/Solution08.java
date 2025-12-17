package com.k3;

public class Solution08 {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        if (n == 1 && nums[0] > threshold) return 0;
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            if (nums[i] % 2 != 0 || nums[i] > threshold) {
                i++;
                continue;
            }
            int j = i;

            while (j + 1 < n && nums[j] % 2 != nums[j + 1] % 2 && nums[j + 1] <= threshold) {
                j++;
            }
            maxLen = Math.max(maxLen, j - i + 1);

            i = j + 1;
        }
        return maxLen;
    }
}
