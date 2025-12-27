package com.k6;

import java.util.*;

public class Solution17 {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 2) {
                ans[0] = num;
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                ans[1] = i;
                break;
            }
        }
        return ans;
    }
}
