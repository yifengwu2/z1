package com.k7;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution43 {
    public int minOperations(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        if (n == 1) return 0;
        stack.push(s.charAt(0));
        int cnt=0;
        for (int i = 1; i < n; i++) {
            if (stack.peek()==s.charAt(i)){
                if (stack.peek()=='0'){
                    stack.push('1');
                }else if (stack.peek()=='1'){
                    stack.push('0');
                }
                cnt++;
            }else {
                stack.push(s.charAt(i));
            }
        }
        return Math.min(n - cnt, cnt);
    }
}
