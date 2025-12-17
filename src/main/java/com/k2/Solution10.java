package com.k2;

public class Solution10 {
    public int appendCharacters(String s, String t) {
        int n = s.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < t.length()) {
                char c = s.charAt(i);
                char c1 = t.charAt(j);
                if (c == c1) {
                    j++;
                }
            }
        }
        return t.length()  - j;
    }
}
