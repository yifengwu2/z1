package com.k6;

import java.util.HashMap;
import java.util.Map;

public class Solution43 {
    public int minLength(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 1) {
                sum += num;
            }
            while (sum >= k && j <= i) {
                min = Math.min(min, i - j + 1);
                int left = nums[j];
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    sum -= left;
                }
                j++;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
