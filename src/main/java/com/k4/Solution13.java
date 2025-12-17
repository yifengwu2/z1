package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution13 {
    public int maxOperations(int[] nums, int k) {
        int n = nums.length;
        //value → frequency
        Map<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int pre = k - nums[i];
            if (map1.getOrDefault(pre, 0) > 0) {
                map1.put(pre, map1.get(pre) - 1);
                count++;
            } else {
                map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
            }
        }
        return count;

    }
}
