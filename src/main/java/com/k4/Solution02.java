package com.k4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution02 {
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        char[] chars = word.toCharArray();
        int maxLen = 0;
        int i = 0;
        while (i < n) {
            int j = i;

            while (j + 1 < n && set.contains(chars[j]) && chars[j] - 'a' <= chars[j + 1] - 'a') {
                j++;
            }
            String s = word.substring(i, j + 1);
            if (s.contains("a") && s.contains("e") && s.contains("i") && s.contains("o") && s.contains("u")) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
            i = j + 1;
        }
        return maxLen;
    }
}
