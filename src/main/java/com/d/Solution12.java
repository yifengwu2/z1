package com.d;

import java.util.*;

/**
 * 优化
 */
public class Solution12 {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> list : map.values()) {
            if (list.size() >= 3) {
                for (int idx = 0; idx <= list.size() - 3; idx++) {
                    Integer n1 = list.get(idx);
                    Integer n3 = list.get(idx + 2);

                    int dist = 2 * (n3 - n1);
                    min = Math.min(min, dist);
                }
            }

        }
         return min == Integer.MAX_VALUE ? -1 : min;
    }
}
