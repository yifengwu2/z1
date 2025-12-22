package com.k5;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution24 {
    public int minLength(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && (c == 'B' && stack.peek() == 'A') || !stack.isEmpty() &&(c == 'D' && stack.peek() == 'C')) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }
}
