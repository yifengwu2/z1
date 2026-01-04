package com.k6;

public class Solution42 {
    public String reversePrefix(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int min = Math.min(k, n);
        for (int i = min - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        for (int i = min; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
