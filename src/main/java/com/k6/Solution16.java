package com.k6;

public class Solution16 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            int j = i;
            while (j < n && nums[j] == 1) {
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
            i = j + 1;
        }
        return maxLen;
    }
}
