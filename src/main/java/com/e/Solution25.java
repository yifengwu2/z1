package com.e;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution25 {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);

            while (map.size() >= set.size()) {
                int num1 = nums[left];
                map.put(num1, map.get(num1) - 1);
                if (map.get(num1) == 0) {
                    map.remove(num1);
                }
                left++;

            }
            res += left;
        }
        return res;
    }
}
