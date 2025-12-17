package com.k4;

public class Solution24 {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;
        int n = corridor.length();

        int len = 0;
        int cnt = 0;
        int count = 0;
        int j = 0;


        while (j < n && cnt < 2) {
            if (corridor.charAt(j) == 'S') {
                cnt++;
            }
            j++;
        }
        if (cnt < 2) return 0;

        int i = n - 1;
        while (i > 0 && count < 2) {
            if (corridor.charAt(i) == 'S') {
                count++;
            }
            i--;
        }
        int i1 = i - j + 2;
        if (i1 > 0) {
            return i1 % MOD;
        }
        return 0;


    }
}
