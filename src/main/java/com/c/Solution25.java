package com.c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution25 {
    public boolean doesAliceWin(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (set.contains(c)) {
                return true;
            }
        }

        return false;
    }
}
