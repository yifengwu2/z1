package com.d;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举
 */
public class Solution13 {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);

            for (int j = i + 1; j < n; j++) {
                if (nums[j] == nums[i]) {
                    list.add(j);
                }
            }
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
