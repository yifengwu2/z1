package com.e;

import java.util.Arrays;

public class Solution10 {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int maxLen = 0;

        int left = 0;
        for (int right = 0; right < n; right++) {

            while (nums[right] - nums[left] >= 2 * k) {
                left++;
            }
            maxLen = Math.max(maxLen, right - left);

        }

        return maxLen;
    }
}
