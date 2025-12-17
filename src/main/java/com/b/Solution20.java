package com.b;

public class Solution20 {
    public int compress(char[] chars) {
        int n = chars.length;
        if (n == 1) return 1;

        int i = 0;
        int j = i;
        int write = i;

        while (i < n) {
            char c = chars[i];
            int count;
            while (j < n && chars[j] == c) {
                j++;
            }
            count = j - i;
            chars[write++] = c;
            if (count > 1) {
                String s = String.valueOf(count);
                for (Character character : s.toCharArray()) {
                    chars[write] = character;
                    write++;
                }
            }
            i = j;

        }

        return write;
    }
}
