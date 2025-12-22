package com.k5;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Solution16 {
    public int calPoints(String[] operations) {
        int n = operations.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            String operation = operations[i];
            if (Objects.equals(operation, "+")) {
                Integer i1 = stack.get(stack.size() - 1);
                Integer i2 = stack.get(stack.size() - 2);
                int i3 = i1 + i2;
                stack.push(i3);
                continue;
            }
            if (Objects.equals(operation, "D")) {
                Integer peek = stack.peek();
                int i1 = peek * 2;
                stack.push(i1);
                continue;
            }
            if (Objects.equals(operation, "C")) {
                stack.pop();
                continue;
            }
            int num = Integer.parseInt(operation);
            stack.push(num);

        }
        int sum = 0;
        int n1 = stack.size();
        for (int i = 0; i <n1; i++) {
            sum += stack.pop();
        }
        return sum;
    }
}
