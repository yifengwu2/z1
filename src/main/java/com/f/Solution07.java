package com.f;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution07 {
    public String reverseVowels(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = n - 1;
        Set<Character> set = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        );

        while (left < right) {
            if (set.contains(chars[left]) && set.contains(chars[right])) {
                char c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
                left++;
                right--;
            } else if (!set.contains(chars[left]) && set.contains(chars[right])) {
                left++;
            } else {
                right--;
            }

        }

        return String.valueOf(chars);
    }
}
