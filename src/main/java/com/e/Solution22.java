package com.e;

public class Solution22 {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int preIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (preIndex != -1) {
                    if (i - preIndex - 1 < k) {
                        return false;
                    }
                }
                preIndex = i;
            }
        }
        return true;
    }
}
