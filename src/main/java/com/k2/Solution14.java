package com.k2;

public class Solution14 {
    public int longestContinuousSubstring(String s) {
        int maxLen = 0;
        int j = 0;
        if (s.length() == 1) return 1;
        for (int i = 1; i < s.length(); i++) {
            int i1 = s.charAt(i) - 'a';
            int i2 = s.charAt(i - 1) - 'a';
            if (i1 - i2 == 1) {
                maxLen = Math.max(maxLen, i - j + 1);
            } else {
                j = i;
            }
        }
        return maxLen;
    }
}
