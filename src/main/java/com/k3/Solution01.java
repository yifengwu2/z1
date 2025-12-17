package com.k3;

public class Solution01 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int i = 2;
        int maxLen = 2;
        while (i < n) {

            if (i >= 2 && nums[i] == nums[i - 1] + nums[i - 2]) {
                int j = i;

                while (j + 1 < n && nums[j + 1] == nums[j] + nums[j - 1]) {
                    j++;
                }
                maxLen = Math.max(maxLen, j - i + 3);

                i = j + 1;
            } else {
                i++;
            }
        }
        return maxLen;
    }
}
