package com.k2;

public class Solution22 {
    public String makeFancyString(String s) {
        int n = s.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            char c = s.charAt(i);
            int j = i;
            while (j < n && c == s.charAt(j)) {
                j++;
            }
            int keep = Math.min(j - i, 2);

            for (int k = 0; k < keep; k++) {
                sb.append(c);
            }
            i = j;
        }
        return sb.toString();
    }
}
