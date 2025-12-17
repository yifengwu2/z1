package com.b;

import java.util.HashSet;
import java.util.Set;

public class Solution09 {
    public boolean isUnique(String astr) {
        int len = astr.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char c = astr.charAt(i);
            if (set.contains(c)){
                return false;
            }
            set.add(c);
        }

        return true;
    }
}
