package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution22 {
    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int goods = 0;
        for (Integer cnt : map.values()) {
            if (cnt > 1) {
                goods += cnt * (cnt - 1) / 2;
            }
        }
        return goods;
    }
}
