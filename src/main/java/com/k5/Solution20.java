package com.k5;

import java.util.*;

public class Solution20 {
    public long calculateScore(String s) {
        int n = s.length();
        Stack<Integer>[] stacks = new Stack[26];
        for (int i = 0; i < 26; i++) {
            stacks[i] = new Stack<>();
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int mir = (char) 219 - c;
            int mirIdx = mir - 'a';

            if (!stacks[mirIdx].isEmpty()) {
                Integer j = stacks[mirIdx].pop();
                sum += j - i;
            } else {
                int idx = c - 'a';
                stacks[idx].push(i);
            }
        }
        return sum;
    }
}
