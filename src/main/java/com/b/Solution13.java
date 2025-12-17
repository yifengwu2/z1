package com.b;

import java.util.HashSet;
import java.util.Set;

public class Solution13 {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();

        for (Character c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
}
