package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution04 {
    public int specialTriplets(int[] nums) {
        final int MOD = 1000000007;
        int n = nums.length;
        long countMax = 0;

        Map<Integer, Integer> rightCount = new HashMap<>();
        for (int x : nums) {
            rightCount.put(x, rightCount.getOrDefault(x, 0) + 1);
        }

        Map<Integer, Integer> leftCount = new HashMap<>();


        for (int i = 0; i < n; i++) {
            int num = nums[i] * 2;

            rightCount.put(nums[i], rightCount.getOrDefault(nums[i], 0) - 1);

            int left = leftCount.getOrDefault(num, 0);

            int right = rightCount.getOrDefault(num, 0);

            countMax = (countMax + (long) left * right) % MOD;


            leftCount.put(nums[i], leftCount.getOrDefault(nums[i], 0) + 1);
        }

        return (int) countMax;
    }
}
