package com.k7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution04 {
    public int vowelConsonantScore(String s) {
        s = s.replaceAll("\\s+", "");
        int n = s.length();
        //元音
        int cnt = 0;
        //辅音
        int count = 0;
        double score;

        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                cnt++;
            }
            if (!Character.isDigit(c) && !set.contains(c)) {
                count++;
            }
        }
        if (count == 0) return 0;

        score = Math.floor((double) cnt / count);

        return (int) score;

    }
}
