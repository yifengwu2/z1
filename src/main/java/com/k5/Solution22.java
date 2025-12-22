package com.k5;

import java.util.*;

public class Solution22 {
    public long minCost(String s, int[] cost) {
        char[] chars = s.toCharArray();
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(chars[i]);
        }
        if (set.size() == 1) {
            return 0;
        }
        long total = Long.MAX_VALUE;
        long totalCost = 0;
        for (int c : cost) totalCost += c;

        List<Character> list = new ArrayList<>(set);
        for (int i = 0; i < list.size(); i++) {
            Character c = list.get(i);
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if (chars[j] == c) {
                    sum += cost[j];
                }
            }
            total =  Math.min(total, totalCost-sum);
        }
        return total;
    }
}
