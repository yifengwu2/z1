package com.k7;

import java.util.*;

public class Solution08 {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                set.add(nums[j]);
                if (set.contains(sum)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
