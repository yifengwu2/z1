package com.k3;

import java.util.Arrays;

public class Solution22 {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i1 = 0; i1 < nums.length; i1++) {
            res[i1] = Integer.bitCount(nums[i1]);
        }
        int i = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && res[j] == res[j + 1]) {
                j++;
            }
            Arrays.sort(nums, i, j + 1);
            i = j + 1;
        }
        for (int j = 1; j < n; j++) {
            if (nums[j] < nums[j-1]) {
                return false;
            }
        }
        return true;
    }
}
