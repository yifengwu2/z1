package com.k5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Solution23 {
    public String removeDuplicates(String s) {
        int n = s.length();
        if (n == 1) return s;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Objects.equals(stack.peek(), c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        StringBuilder reverse = sb.reverse();
        return reverse.toString();
    }
}
