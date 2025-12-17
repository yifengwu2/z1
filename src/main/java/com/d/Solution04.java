package com.d;

import java.util.HashSet;
import java.util.Set;

public class Solution04 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left ++;
            }
            set.add(c);

            maxLen = Math.max(maxLen, right - left + 1);


        }

        return maxLen;
    }
}
