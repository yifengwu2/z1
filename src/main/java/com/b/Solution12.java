package com.b;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//错误版
public class Solution12 {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Integer integer : map.values()) {
            if (integer % 2 == 0) {
                continue;
            }
            if (set.contains(integer)) {
                return false;
            }
            set.add(integer);
        }

        return true;
    }

}
