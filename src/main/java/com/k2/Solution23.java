package com.k2;

public class Solution23 {
    public boolean checkZeroOnes(String s) {
        int n = s.length();
        int i = 0;
        int maxLen1 = 0;
        int maxLen0 = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == '1') {
                int j = i;
                while (j < n && s.charAt(j) == c) {
                    j++;
                }
                maxLen1 = Math.max(maxLen1, j - i);
                i = j;
            } else if (c == '0') {
                int j = i;
                while (j < n && s.charAt(j) == c) {
                    j++;
                }
                maxLen0 = Math.max(maxLen0, j - i);
                i = j;
            }
        }
        return maxLen1 > maxLen0;
    }
}
