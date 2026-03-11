package com.k7;

public class Solution55 {
    public int bitwiseComplement(int n) {
        if (n==0) return 1;
        String s = Integer.toBinaryString(n);
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                sb.append(0);
            } else {
                sb.append(1);
            }
        }
        return Integer.parseInt(sb.toString(),2);
    }
}
