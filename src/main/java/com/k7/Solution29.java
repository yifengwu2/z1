package com.k7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution29 {
    public List<Long> mergeAdjacent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.get(nums[i]) >= 2) {
                map.put(nums[i], 0);
                int num = nums[i] * 2;
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        List<Long> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                list.add(Long.valueOf(entry.getKey()));
            }
        }
        if (list.size() == nums.length) {
            List<Long> list1 = new ArrayList<>();
            for (int num : nums) {
                list1.add((long) num);
            }
            return list1;
        }
        return list;
    }
}
