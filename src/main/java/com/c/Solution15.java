package com.c;

public class Solution15 {
    public int triangularSum(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;

        int[] res = new int[n - 1];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int before = nums[i - 1];

            if (cur + before >= 10) {
                int num = (cur + before) % 10;
                res[i - 1] = num;
            } else {
                res[i - 1] = cur + before;
            }

        }

        return triangularSum(res);
    }
}
