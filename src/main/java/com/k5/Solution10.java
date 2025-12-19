package com.k5;

import java.util.HashMap;
import java.util.Map;

public class Solution10 {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1000000007;
        int n = arr.length;
        long total = (long)n * (n + 1) / 2;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prev = 0;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            prev += arr[i];
            int mod = (prev % 2 + 2) % 2;
            cnt += map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return (int) ((total - cnt) % MOD);
    }
}
