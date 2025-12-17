package com.k2;

public class Solution20 {
    public int maxPower(String s) {
        int n = s.length();
        int i = 0;
        int maxLen = 0;
        while (i < n) {
            char c = s.charAt(i);

            int j = i;
            while (j < n && c == s.charAt(j)) {
                j++;
            }
            maxLen = Math.max(maxLen, j - i + 1);

            i = j;
        }
        return maxLen;
    }
}
