package com.d;

public class Solution02 {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int maxLen = 0;

        int countOne = 0;
        int countZero = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                countZero++;
            } else {
                countOne++;
            }
            // 当前窗口中 0 的数量超过了 k，无法全部改成 1，需要缩窗
            while (countZero > k) {
                if (nums[left] == 0) {
                    countZero--;
                } else {
                    countOne--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
