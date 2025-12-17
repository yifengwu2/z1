package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution18 {
    public long interchangeableRectangles(int[][] rectangles) {
        //存划分后的分数，和频率
        int n = rectangles.length;
        Map<String, Integer> map = new HashMap<>();
        long total = 0;
        for (int i = 0; i < n; i++) {
            int[] rectangle = rectangles[i];
            int w = rectangle[0];
            int h = rectangle[1];
            int gcd = gcd(w, h);
            w = w / gcd;
            h = h / gcd;
            String s = w + "/" + h;
//            if (map.containsKey(s)) {
//                map.put(s, map.get(s) + 1);
//            }
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer cnt = entry.getValue();
            if (cnt > 1) {
                total += (long) cnt * (cnt - 1) / 2;
            }
        }
        return total;
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
