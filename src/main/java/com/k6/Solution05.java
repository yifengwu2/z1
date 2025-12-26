package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Solution05 {
    public String reverseParentheses(String s) {
        if (s == null) return "";
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && Objects.equals(c, ')')) {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && !Objects.equals(stack.peek(), '(')) {
                    Character pop = stack.pop();
                    sb.append(pop);
                }
                stack.pop();
                String str = sb.toString();
                for (Character ch : str.toCharArray()) {
                    stack.push(ch);
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();

    }
}
