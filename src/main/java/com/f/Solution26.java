package com.f;

public class Solution26 {
    public int minAllOneMultiple(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        return dfs(1 % k, 1, k);
    }

    private int dfs(int rem, int len, int k) {
        if (rem % k == 0) {
            return len;
        }
        if (len > k) {
            return -1;
        }
        int nextRem = (rem * 10 + 1) % k;
        return dfs(nextRem, len+1,k);
    }
}
