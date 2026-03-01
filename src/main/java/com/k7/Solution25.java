package com.k7;

import java.util.*;

public class Solution25 {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer bulb : bulbs) {
            map.put(bulb, map.getOrDefault(bulb, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list);
        return list;
    }
}
