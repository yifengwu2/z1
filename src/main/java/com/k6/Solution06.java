package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution06 {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                open++;
                sb.append(c);
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        StringBuilder res = new StringBuilder();
        int close = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                close++;
                res.append(c);
            } else if (c == '(') {
                if (close > 0) {
                    close--;
                    res.append(c);
                }
            } else {
                res.append(c);
            }
        }
        return res.reverse().toString();
    }
}
