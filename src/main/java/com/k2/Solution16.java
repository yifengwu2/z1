package com.k2;

public class Solution16 {
    public int maxPower(String s) {
        int n = s.length();
        int maxPower = 1;
        int j = 0;
        for (int i = 1; i < n; i++) {
            int i1 = s.charAt(i) - 'a';
            int i2 = s.charAt(i - 1) - 'a';
            if (i1 == i2) {
                maxPower = Math.max(maxPower, i - j + 1);
            } else {
                j = i;
            }
        }
        return maxPower;
    }
}
