package com.c;

import java.util.*;

public class Solution14 {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] split = text.split("\\s+");

        char[] chars = brokenLetters.toCharArray();

        int count = 0;

        for (String s : split) {
            boolean flag = false;
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                set.add(c);
            }
            //判断
            for (char aChar : chars) {
                if (set.contains(aChar)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                count++;
            }

        }

        return count;
    }
}
