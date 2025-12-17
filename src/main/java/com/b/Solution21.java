package com.b;

public class Solution21 {
    public long removeZeros(long n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int num = chars[i] - '0';
            if (num == 0) {
                continue;
            }
            sb.append(num);
        }
        String s1 = sb.toString();
        return Long.parseLong(s1);

    }
}
