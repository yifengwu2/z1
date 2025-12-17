package com.k2;

public class Solution24 {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            int num = nums[i];
            int j = i+1;

            while (j < n && nums[j] - num > 0) {
                num = nums[j];
                j++;
            }
            maxLen = Math.max(maxLen, j - i);

            i = j;
        }
        return maxLen;
    }
}
