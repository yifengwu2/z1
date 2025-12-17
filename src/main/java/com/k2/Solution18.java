package com.k2;

public class Solution18 {
    public int numSub(String s) {
        final long MOD = 1000000007;
        int n = s.length();
        long count = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                count += i - j+1;
            } else {
                j = i + 1;
            }
        }
        return (int) (count % MOD);
    }
}
