package com.k6;

public class Solution34 {
    public String largestEven(String s) {
        int n = s.length();
        int j = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '2') {
                j = i;
                break;
            }
        }
        if (j == -1) {
            return "";
        }
        return s.substring(0, j + 1);
    }
}
