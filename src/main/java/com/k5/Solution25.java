package com.k5;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution25 {
    public String makeGood(String s) {
        int n = s.length();
        if (n == 1) return s;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
                Character peek = stack.peek();
            if (!stack.isEmpty() &&
                    ((Character.isLowerCase(stack.peek()) && Character.isUpperCase(c))
                            || (Character.isUpperCase(peek) && Character.isLowerCase(c)))
                    && Character.toLowerCase(peek) == Character.toLowerCase(c)) {
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
