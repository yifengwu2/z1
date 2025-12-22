package com.k5;

import java.util.Stack;

public class Solution15 {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#' && !stack1.isEmpty()) {
                stack1.pop();
            } else if (c != '#') {
                stack1.push(c);
            }
        }
        Stack<Character> stack2 = new Stack<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '#' && !stack2.isEmpty()) {
                stack2.pop();
            } else if (c != '#') {
                stack2.push(c);
            }

        }
        return stack1.equals(stack2);
    }
}
