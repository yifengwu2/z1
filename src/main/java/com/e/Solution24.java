package com.e;

public class Solution24 {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int left = 0;
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        int res = 0;
        for (int right = 0; right < n; right++) {
            char c = chars[right];
            count[c - 'a']++;

            while (count[c - 'a'] >= k) {
                char c1 = chars[left];
                count[c1 - 'a']--;
                left++;
            }
            res += left;

        }
        return res;
    }
}
