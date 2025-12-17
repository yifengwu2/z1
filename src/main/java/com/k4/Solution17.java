package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution17 {
    public int minimumCardPickup(int[] cards) {
        int maxLen = Integer.MAX_VALUE;
        int n = cards.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int card = cards[i];
            if (map.containsKey(card)) {
                maxLen = Math.min(maxLen, i - map.get(card) + 1);
            }
            map.put(card, i);
        }
        return maxLen == Integer.MAX_VALUE ? -1 : maxLen;
    }
}
