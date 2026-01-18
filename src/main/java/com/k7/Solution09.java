package com.k7;

import java.util.HashMap;
import java.util.Map;

public class Solution09 {
    public long countPairs(String[] words) {
        int n = words.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            char c = word.charAt(0);
            int k = (97 - c + 26) % 26;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                char c1 = word.charAt(j);
                char chr = (char) ((c1 - 'a' + k) % 26 + 'a');
                sb.append(chr);
            }
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        long total = 0;
        for (Integer value : map.values()) {
            if (value >= 2) {
                total += (long) value * (value - 1) / 2;
            }
        }
        return total;
    }
}
