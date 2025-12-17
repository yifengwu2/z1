package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution23 {
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int t = time[i];
                int reTime = t % 60;
                map.put(reTime, map.getOrDefault(reTime, 0) + 1);

        }

        long timeCount = 0;
        timeCount += (long) map.getOrDefault(0, 0) * (map.getOrDefault(0, 0) - 1) / 2;
        timeCount += (long) map.getOrDefault(30, 0) * (map.getOrDefault(30, 0) - 1) / 2;
        for (int r = 1; r < 30; r++) {
            timeCount += (long) map.getOrDefault(r, 0) * map.getOrDefault(60 - r, 0);
        }
        return (int) timeCount;

    }
}
