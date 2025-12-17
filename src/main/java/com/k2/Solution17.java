package com.k2;

public class Solution17 {
    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long count = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count += i - j + 1;
            } else {
                j = i;
                j++;
            }
        }
        return count;
    }
}
