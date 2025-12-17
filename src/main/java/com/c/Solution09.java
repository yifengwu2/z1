package com.c;

import java.util.HashMap;
import java.util.Map;

public class Solution09 {
    public int[] getSneakyNumbers(int[] nums) {
        int[] res = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        int k = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                res[k++] = entry.getKey();
            }
        }

        return res;
    }
}
