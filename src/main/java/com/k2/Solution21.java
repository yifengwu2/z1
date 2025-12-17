package com.k2;

public class Solution21 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        if (n == 1 && nums[0] == 0) {
            return 0;
        } else if (n == 1 && nums[0] == 1) {
            return 1;
        }
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            int num = nums[i];
            if (num == 1) {
                int j = i;
                while (j < n && num == nums[j]) {
                    j++;
                }
                maxLen = Math.max(maxLen, j - i);

                i = j;
            } else {
                i++;
            }
        }
        return maxLen;

    }
}
