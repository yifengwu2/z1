package com.b;

public class Solution07 {
    public boolean hasSameDigits(String s) {

        while (s.length() > 2) {
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length() - 1; j++) {
                int start = chars[j] - '0';
                int end = chars[j + 1] - '0';
                sb.append ((start + end) % 10);
            }
            s = sb.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}
