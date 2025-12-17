package com.c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution29 {
    public int maxFreqSum(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                map1.put(c, map1.getOrDefault(c, 0) + 1);
            }else {
                map2.put(c, map2.getOrDefault(c, 0) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Integer value = entry.getValue();
            max1 = Math.max(max1, value);
        }
        for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
            Integer value = entry.getValue();
            max2 = Math.max(max2, value);
        }
        return max1+max2;
    }
}
