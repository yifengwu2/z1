package com.d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution09 {
    public int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int n = s.length();
        int left = 0;

        int maxCount = 0;

        int count = 0;
        for (int right = 0; right < n; right++) {
            if (set.contains(s.charAt(right))) {
                count++;
            }
            while (right - left + 1 > k) {
                if (set.contains(s.charAt(left))) {
                    count--;
                }
                left++;

            }
            maxCount = Math.max(maxCount, count);

        }
        return maxCount;
    }
}
