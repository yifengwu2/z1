package com.k;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class Solution17 {
    public String addSpaces(String s, int[] spaces) {
        Set<Integer> set = new HashSet<>();
        for (int num : spaces) {
            set.add(num);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(i)) {
                sb.append(" ");
                sb.append(s.charAt(i));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
