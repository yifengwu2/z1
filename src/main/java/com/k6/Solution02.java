package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution02 {
    public String removeOuterParentheses(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (cnt > 0) {
                    sb.append(c);
                }
                cnt++;
            } else {
                cnt--;
                if (cnt > 0) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
