package com.k3;

import java.util.HashMap;
import java.util.Map;

public class Solution26 {
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        int dayTime = 24;
        long cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int hour = hours[i];
            int r = hour % dayTime;
            //搭档
            int target = (dayTime - r) % 24;

            cnt += map.getOrDefault(target, 0);

            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return cnt;
    }
}