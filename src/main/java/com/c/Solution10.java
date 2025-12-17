package com.c;

import java.util.HashMap;
import java.util.Map;

public class Solution10 {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer i : map.values()) {
            maxFreq = Math.max(maxFreq, i);
        }

        for (Integer i : map.values()) {
            if (i == maxFreq) {
                count++;
            }
        }
        sum = count * maxFreq;


        return sum;
    }
}
