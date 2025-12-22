package com.k5;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution19 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        int n = pushed.length;
        for (int i = 0; i < n; i++) {
            int num = pushed[i];
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
