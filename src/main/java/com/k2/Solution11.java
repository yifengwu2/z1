package com.k2;

public class Solution11 {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n = str1.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < str2.length()) {
                int i1 = str1.charAt(i) - 'a';
                int i2 = str2.charAt(j) - 'a';
                if (i1 == 26) {
                    i1 = 0;
                }
                if (Math.abs(i1 - i2) == 0 || (i1 + 1) % 26 == i2) {
                    j++;
                }
            }
        }
        return j == str2.length();
    }
}
