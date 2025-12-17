package com.k3;

public class Solution02 {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int i = 0;
        int ans = 0;
        int preCount = 0;
        while (i < n) {
            char c = chars[i];

            int j = i;

            int count = 0;

            while (j < n && c == chars[j]) {
                count++;
                j++;
            }
            ans += Math.min(preCount, count);

            preCount = count;

            i = j;
        }
        return ans;

    }
}
