package com.e;

public class Solution05 {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int left = 0;
        int maxLen = 0;
        int count = 0;
        for (int right = 0; right < n; right++) {
            count += Math.abs(chars1[right] - chars2[right]);

            while (count > maxCost) {
                int abs = Math.abs(chars1[left] - chars2[left]);
                count -= abs;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;
    }
}
