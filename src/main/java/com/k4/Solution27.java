package com.k4;

import java.util.*;

public class Solution27 {
    public String reverseWords(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        String[] split = s.trim().split("\\s+");
        if (split.length == 0) return "";

        String s1 = split[0];
        int count = 0;
        for (Character c : s1.toCharArray()) {
            if (set.contains(c)) {
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);
        sb.append(" ");
        for (int i = 1; i < split.length; i++) {
            int cnt = 0;
            String str = split[i];
            for (int i1 = 0; i1 < str.length(); i1++) {
                if (set.contains(str.charAt(i1))) {
                    cnt++;
                }
            }
            if (cnt == count) {
                StringBuilder sb1 = new StringBuilder(str);
                String string = sb1.reverse().toString();
                sb.append(string);
                sb.append(" ");
            } else {
                sb.append(str);
                sb.append(" ");
            }
        }
        if (sb.length()>0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();

    }
}
