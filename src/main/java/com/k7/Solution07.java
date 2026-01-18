package com.k7;

import java.util.HashSet;
import java.util.Set;

public class Solution07 {
    public int residuePrefixes(String s) {
        int cnt = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            set.add(c);
            if (set.size() == (i + 1) % 3) {
                cnt++;
            }
        }
        return cnt;
    }
}
