package com.e;

import java.util.HashSet;
import java.util.Set;

public class Solution01 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0;
        Set<Character> set = new HashSet<>();
        int maxLen = 0;

        for (int right = 0; right < n; right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }
}
