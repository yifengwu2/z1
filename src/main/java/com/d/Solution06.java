package com.d;

import java.util.ArrayList;
import java.util.List;

public class Solution06 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        int count = 0;
        int left = 0;
        if (nums.length == 1) {
            return 1;
        }

        for (int right = 1; right < n; right++) {
            int num = nums[right];
            if (num < nums[right - 1]) {
                count++;
            }

            while (count > 1) {
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }
}
