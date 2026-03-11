package com.k7;

import java.util.Map;
import java.util.Stack;

public class Solution56 {
    public boolean isValid(String s) {
        int n = s.length();
        Map<Character, Character> map = Map.of(']', '[', '}', '{', ')', '(');
        Stack<Character> stack = new Stack();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if ( map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();


    }
}
