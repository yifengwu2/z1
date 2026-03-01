package com.k7;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution22 {
    public String trimTrailingVowels(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int index = -1;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "";
        }
        for (int i = 0; i <= index; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();

    }
}
