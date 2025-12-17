package com.d;

public class Solution07 {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;

        for (int left = 0; left < n; left++) {
            int countTarget = 0;
            for (int right = left; right < n; right++) {
                if (nums[right] == target) {
                    countTarget++;
                }
                if (countTarget > (right - left + 1) / 2) {
                    total++;
                }
            }

        }
        return total;
    }


}
