package com.k4;

import java.util.*;
import java.util.stream.Collectors;

public class Solution19 {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        Set<String> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        set.add("electronics");
        set.add("grocery");
        set.add("pharmacy");
        set.add("restaurant");
        int n = code.length;
        for (int i = 0; i < n; i++) {
            if (set.contains(businessLine[i]) && isActive[i] && Judge(code, i)) {
                list.add(i);
            }
        }
        Map<String, Integer> map = Map.of("electronics", 1, "grocery", 2, "pharmacy", 3, "restaurant", 4);
        return list.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                String s1 = businessLine[i1];
                String s2 = businessLine[i2];
                Integer i = map.get(s1);
                Integer j = map.get(s2);
                int cmd = i.compareTo(j);
                if (cmd != 0) return cmd;
                return code[i1].compareTo(code[i2]);
            }
        }).map(i -> code[i]).collect(Collectors.toList());

    }

    private boolean Judge(String[] code, int i) {
        Set<Character> set = new HashSet<>(Arrays.asList('@', '!', '#', '$', '%', '^', '&', '*', '{', '}'));
        String s = code[i];
        if (s == null || Objects.equals(s, "")) {
            return false;
        }
        s = s.toLowerCase();
        for (Character c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }


}
