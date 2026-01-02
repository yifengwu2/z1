package com.f;

import java.util.HashMap;
import java.util.Map;

public class Solution34 {
    public int repeatedNTimes(int[] nums) {
        int len = nums.length;
        int n = len / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int x = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == n) {
                x = entry.getKey();
            }
        }
        return x;
    }
}