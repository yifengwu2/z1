package com.e;

import java.util.Stack;

public class Solution23 {
    public boolean isOneBitCharacter(int[] bits) {
        Stack<Integer> stack = new Stack<>();
        for (int i = bits.length - 1; i >= 0; i--) {
            stack.add(bits[i]);
        }
        while (stack.size() > 1) {
            if (stack.peek() == 1) {
                stack.pop();
                stack.pop();
            } else {
                stack.pop();
            }
        }
        return !stack.isEmpty();

    }
}
