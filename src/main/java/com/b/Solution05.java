package com.b;

import java.util.Map;
import java.util.Stack;

public class Solution05 {
    public int calculate(String s) {
        //去掉所有空格
        s = s.replaceAll("\\s+", "");

        int len = s.length();
        char[] chars = s.toCharArray();

        //map存优先级
        Map<Character, Integer> map = Map.of('+', 1,
                '-', 1,
                '*', 2,
                '/', 2);


        //存字符
        Stack<Character> operator = new Stack<>();
        //存数字
        Stack<Integer> operands = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < chars.length && Character.isDigit(chars[i])) {
                    sb.append(chars[i++]);
                }
                i--;
                operands.push(Integer.valueOf(sb.toString()));
            } else {
                if (isOperator(c)) {
                    // 高优先级或同级的先计算
                    while (!operator.isEmpty() && map.get(operator.peek()) >= map.get(c)) {
                        evaluate(operator, operands);
                    }
                    operator.push(c);// 当前操作符入栈
                }

            }

        }
        //正确位置：for 循环结束后，清空操作符栈
        while (!operator.isEmpty()) {
            evaluate(operator, operands);
        }

        return operands.pop();
    }

    private void evaluate(Stack<Character> operator, Stack<Integer> operands) {
        Integer right = operands.pop();
        Character op = operator.pop();
        Integer left = operands.pop();

        int res = calculator(left, op, right);
        operands.push(res);

    }

    private int calculator(Integer left, Character op, Integer right) {
        if (isOperator(op)) {
            return switch (op) {
                case '+' -> left + right;
                case '-' -> left - right;
                case '*' -> left * right;
                case '/' -> left / right;
                default -> throw new IllegalArgumentException("字符操作符错误" + op);
            };
        }
        return 0;
    }

    private boolean isOperator(Character character) {
        return character == '+' || character == '-' || character == '*' || character == '/';

    }
}
