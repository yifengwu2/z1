package com.k3;

import java.util.HashMap;
import java.util.Map;

public class Solution27 {
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        int minute = 60;
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int t = time[i];
            //自己
            int t1 = t % minute;
            //搭档
            int target = (minute - t1) % minute;
            //找之前有没有
            cnt += map.getOrDefault(target, 0);

            //把自己存进去
            map.put(t1, map.getOrDefault(t1, 0) + 1);
        }
        return cnt;
    }
}
