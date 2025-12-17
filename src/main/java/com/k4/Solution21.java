package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution21 {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long total = (long) n * (n - 1) / 2;
        //值，索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = i - nums[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long goods = 0;
        for (Integer cnt : map.values()) {
            if (cnt > 1) {
                goods += (long) cnt * (cnt - 1) / 2;
            }
        }
        return total - goods;
    }
}
