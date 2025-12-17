package com.c;

import java.util.*;

public class Solution34 {
    public String addSpaces(String s, int[] spaces) {
        List<Integer> list = new ArrayList<>();
        for (Integer space : spaces) {
            list.add(space);
        }
        Set<Integer> set = new HashSet<>(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i >= 0 && set.contains(i)) {
                sb.append(" ");
                sb.append(s.charAt(i));
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
