package com.k2;

public class Solution07 {
    public int maxDistinct(String s) {
        int[] res = new int[26];
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (res[chars[i] - 'a'] != 0) {
                continue;
            }
            int c = chars[i] - 'a';
            res[c]++;
            count++;
        }
        return count;
    }
}
