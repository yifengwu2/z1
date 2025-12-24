package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Solution01 {
    public int maxDepth(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            stack.push(c);
            if (c == '(') {
                cnt++;

            }
            if (!stack.isEmpty() && c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                max = Math.max(max, cnt);
                cnt=0;
            }
        }
        return max;
    }
}
