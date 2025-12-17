package com.f;

import java.util.HashSet;
import java.util.Set;

public class Solution12 {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        Set<String> set = new HashSet<>();
        char[] chars = s.toCharArray();
        boolean[] booleans = new boolean[26]; // 标记是否已处理过该字符


        for (int i = 0; i < n; i++) {
            char c = chars[i];
            int idx = c - 'a';
            if (booleans[idx]) continue;
            booleans[idx] = true;

            int j = s.lastIndexOf(c);
            if (j - i < 2) continue;

            for (int k = i + 1; k < j; k++) {
                String pal = "" + c + chars[k] + chars[j];
                set.add(pal);
            }
        }
        return set.size();
    }
}
