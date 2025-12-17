package com.c;

import java.util.Arrays;

public class Solution18 {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        //先固定最长的那一条边
        int n = nums.length;
        Arrays.sort(nums);

        int count = 0;

        //枚举最长的那条边
        // 枚举最长边的下标 k，从 n-1 到 2
        for (int k = n - 1; k >= 2; k--) {
            //最长边
            int c = nums[k];

            int l = 0;
            int r = k - 1;

            while (l < r) {
                if (nums[l] + nums[r] >= c) {
                    count += (r - l); // l 到 r-1 都可以和 j,k 构成三角形
                    r--; // 尝试更小的次长边
                } else {
                    l++;
                }
            }

        }
        return count;
    }
}
