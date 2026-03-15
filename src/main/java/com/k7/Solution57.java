package com.k7;

public class Solution57 {
    public int countCommas(int n) {
        if (n < 1000) return 0;
        String s = String.valueOf(n);
        int n1 = s.length();
        int i = n1 / 3;
        return (n -999) * i;
    }
}
