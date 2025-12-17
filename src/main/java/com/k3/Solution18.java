package com.k3;

public class Solution18 {
    public int countHomogenous(String s) {
        final int MOD = 1000000007;
        int n = s.length();
        int i = 0;
        long ans = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && s.charAt(j) == s.charAt(j + 1)) {
                j++;
            }
            int len = j - i + 1;
                ans += (long) len * (len + 1) / 2;

            i = j + 1;
        }
        return (int) (ans % MOD);
    }
}
