package com.k4;

import java.util.Arrays;
import java.util.Map;

public class Solution26 {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int sumMax = 0;
        int n=nums.length;
        for (int i = n - 1; i >= i - k; i--) {
            sumMax += nums[i];
        }

        int sumMin = 0;
        for (int i = 0; i < k; i++) {
            sumMin += nums[i];
        }
        return Math.abs(sumMax - sumMin);


    }
}
