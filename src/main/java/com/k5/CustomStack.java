package com.k5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CustomStack {
    private Deque<Integer> stack;
    private int maxSize;

    public CustomStack(int maxSize) {
        this.stack = new ArrayDeque<>(maxSize);
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (stack.size() < maxSize) {
            stack.push(x);
        }
    }

    public int pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return -1;
    }

    public void increment(int k, int val) {
        List<Integer> list = new ArrayList<>();
        if (stack.size() < k) {
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int i = pop + val;
                list.add(i);
            }
            List<Integer> reversed = list.reversed();
            for (Integer num : reversed) {
                stack.push(num);
            }
        } else {
            int i = stack.size() - k;
            int i1 = stack.size() - i;
            List<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                list1.add(stack.pop());
            }
            for (int l = 0; l < i1; l++) {
                list2.add(stack.pop() + val);
            }
            List<Integer> reversed1 = list1.reversed();
            List<Integer> reversed2 = list2.reversed();
            for (Integer num : reversed2) stack.push(num);
            for (Integer num : reversed1) stack.push(num);
        }
    }
}
