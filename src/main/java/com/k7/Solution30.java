package com.k7;

import java.util.*;

public class Solution30 {
    public List<Long> mergeAdjacent(int[] nums) {
        int n = nums.length;
        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            while (!stack.isEmpty() && stack.peek() == x) {
                stack.pop();
                x += x;
            }
            stack.push(x);
        }
        List<Long> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }
}
