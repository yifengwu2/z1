package com.e;

import java.util.Arrays;

public class Solution04 {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;


        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < n; right++) {
            int num = nums[left];

            while (num > nums[left] * k) {
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);

        }

        return n - maxLen;
    }
}
