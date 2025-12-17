package com.f;

import java.util.Map;

public class Solution09 {
    public int minimumLength(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = n - 1;

        while (left < right && chars[left] == chars[right]) {
            char ch = chars[left];
            if (ch == chars[right]) {
                left++;
                right--;
                while (left <= right && chars[right] == ch) {
                    right--;
                }
                while (left <= right && chars[left] == ch) {
                    left++;
                }
            }
        }
        return Math.max(0, right - left + 1);
    }
}
