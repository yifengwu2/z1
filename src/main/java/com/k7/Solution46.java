package com.k7;

public class Solution46 {
    public int minimumPrefixLength(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        for (int i = n - 1; i > 0; i++) {
            if (nums[i] - nums[i - 1] != 1) {
                return i + 1;
            }
        }
        return 0;

    }
}
