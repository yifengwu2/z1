package com.e;

public class Solution09 {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        if (n < 2) return n;
        int countd = 0;
        int left = 0;
        int maxLen = 0;
        for (int right = 1; right < n ; right++) {
            char c = chars[right];
            if (c == chars[right-1]) {
                countd++;
            }
            while (countd > 1) {
                if (chars[left] == chars[left + 1]) {
                    countd--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;
    }
}
