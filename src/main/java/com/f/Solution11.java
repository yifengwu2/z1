package com.f;

public class Solution11 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        int left = 0;
        int right = n - 1;

        int[] res = new int[n];
        int k = n - 1;

        while (left <= right) {
            if (nums[left] < nums[right]) {
                res[k--] = nums[right];
                right--;
            } else {
                res[k--] = nums[left];
                left++;
            }
        }
        return res;
    }
}
