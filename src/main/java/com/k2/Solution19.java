package com.k2;

public class Solution19 {
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

            int len = j - i;

            int keep = Math.min(len, 2);
            for (int k = 0; k < keep; k++) {
                sb.append(c);
            }
            i = j;

        }
        return sb.toString();
    }
}
