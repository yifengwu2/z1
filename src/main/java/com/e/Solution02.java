package com.e;

public class Solution02 {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] count = new int[26];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            while (count[c - 'a'] >= 2) {
                char c1 = s.charAt(left);
                count[c1 - 'a']--;
                left++;
            }

            count[c - 'a']++;

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;
    }
}
