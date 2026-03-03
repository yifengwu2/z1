package com.k7;

import java.util.HashMap;
import java.util.Map;

public class Solution34 {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int mod = 1000000007;
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(cnt, map.getOrDefault(cnt, 0) + 1);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > delay && entry.getValue() < forget) {
                    cnt++;
                }
                if (entry.getValue() == forget) {
                    map.put(entry.getKey(), 0);
                }
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                count++;
            }
        }
        return count % mod;
    }
}
