package com.f;

public class Solution22 {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;
        String s = String.valueOf(n);
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        long sum = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int num = c - '0';
            if (num != 0) {
                sb.append(num);
                sum += num;
            }
        }
        long x = Long.parseLong(sb.toString());

        return x * sum;
    }
}
