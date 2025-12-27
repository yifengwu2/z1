package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution11 {
    public int clumsy(int n) {
        Deque<Integer> nums = new ArrayDeque<>();
        nums.push(n);
        int num = n - 1;//下一个要处理的数字
        for (int i = 0; i < n - 1; i++) {
            char op = (i % 4 == 0) ? '*' : (i % 4 == 1) ? '/' : (i % 4 == 2) ? '+' : '-';

            if (op == '*') {
                nums.push(nums.pop() * num);
            } else if (op == '/') {
                nums.push(nums.pop() / num);
            } else if (op == '+') {
                nums.push(num);
            } else {
                nums.push(-num);
            }
            num--;
        }
        int res = 0;
        while (!nums.isEmpty()) res += nums.pop();
        return res;
    }
}
