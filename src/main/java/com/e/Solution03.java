package com.e;

public class Solution03 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int count_1 = 0;
        int count_0 = 0;
        for (int num : nums) {
            if (num == 1) {
                count_1++;
            }
        }

        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) count_0++;

            while (count_0 > 1) {
                if (nums[left] == 0) {
                    count_0--;
                }
                left++;

            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen-1;
    }
}
