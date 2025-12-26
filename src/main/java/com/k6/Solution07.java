package com.k6;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n+1; j++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (i < j) {
                    if (customers.charAt(i) == 'N') {
                        cnt++;
                    }
                } else {
                    //关门
                    if (customers.charAt(i) == 'Y') {
                        cnt++;
                    }
                }
            }
            map.put(j, cnt);
            min = Math.min(min, cnt);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == min) {
                return entry.getKey();
            }
        }
        return 0;


    }
}
