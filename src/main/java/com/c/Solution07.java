package com.c;

import java.util.Stack;

public class Solution07 {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                Integer top = stack.peek();
                if (top < -asteroid) {
                    // 栈顶小，爆炸，继续看下一个
                    stack.pop();
                } else if (top == -asteroid) {
                    stack.pop();
                    asteroid = 0;//标记当前已消失
                    break;
                } else {
                    // 栈顶大，当前行星爆炸
                    asteroid = 0;
                    break;
                }
            }
            //如果当前新星没有被摧毁，就入栈
            if (asteroid != 0) {
                stack.push(asteroid);
            }
        }
        int[] res = new int[stack.size()];

        for (int i = 0; i < stack.size(); i++) {
            res[i] = stack.get(i);
        }
        return res;
    }
}
