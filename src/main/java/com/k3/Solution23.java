package com.k3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution23 {
    public int similarPairs(String[] words) {
        int n = words.length;
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            for (; j < n; j++) {
                if (isSimple(words[i], words[j])) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean isSimple(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (Character c : s1.toCharArray()) {
            set.add(c);
        }
        for (Character c : s2.toCharArray()) {
            set2.add(c);
        }
        if (set2.size() != set.size()) {
            return false;
        }
        return set.equals(set2);

    }
}
