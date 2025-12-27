package com.k6;

import java.util.Arrays;

public class Solution14 {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            int j = i % n;
            res[i] = nums[j];
        }
        return res;

    }
}
