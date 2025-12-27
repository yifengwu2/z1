package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Solution13 {
    public int evalRPN(String[] tokens) {
        Deque<String> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                String a = stack.pop();
                String b = stack.pop();
                int i1 = Integer.parseInt(a);
                int i2 = Integer.parseInt(b);

                int cal = calculate(i2,i1, token);
                String s = String.valueOf(cal);
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String s) {
        return Objects.equals(s, "*") || Objects.equals(s, "-") || Objects.equals(s, "+") || Objects.equals(s, "/");

    }

    private int calculate(int a, int b, String op) {
        switch (op) {
            case "*" -> {
                return a * b;
            }
            case "+" -> {
                return a + b;
            }
            case "/" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;
            }
            default -> {
                return a - b;
            }
        }

    }
}
