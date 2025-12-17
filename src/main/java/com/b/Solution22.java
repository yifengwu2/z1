package com.b;

import java.util.Arrays;

public class Solution22 {
    public long maxAlternatingSum(int[] nums) {
        long score = 0;
        long[] clone = new long[nums.length];

        for (int i = 0; i < clone.length; i++) {
            clone[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(clone);
        int left = 0;
        int right = clone.length - 1;

        boolean flag = true;
        while (left <= right) {
            if (flag) {
                score += clone[right--];
            } else {
                score -= clone[left++];
            }
            flag = !flag;
        }


        return score;
    }
}
