package com.k7;

import java.util.HashSet;
import java.util.Set;

public class Solution06 {
    public int minOperations(int[] nums, int[] target) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                set.add(nums[i]);
            }
        }
        return set.size();
    }
}
