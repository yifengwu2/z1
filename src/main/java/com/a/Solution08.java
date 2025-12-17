package com.a;

import java.util.*;

public class Solution08 {
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        //维护字典序最小字符串
        String best = s;

        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            //更新最小字典序
            if (curr.compareTo(best) < 0) {
                best = curr;
            }
            //操作一：累加（加a到奇数位）
            String added = addOdd(curr, a);
            if (visited.add(added)) {
                queue.offer(added);
            }

            //操作2:轮转b位
            String rotated = rotate(curr, b);
            if (visited.add(rotated)) {
                queue.offer(rotated);
            }
        }
        return best;
    }

    //向右轮转b位
    private String rotate(String s, int b) {
        int n = s.length();
        b = b % n;
        return s.substring(n - b) + s.substring(0, n - b);
    }

    //对奇数下标加a
    private String addOdd(String s, int a) {
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i += 2) {
            //字符转数字：'5'->5
            int digit = chars[i] - '0';
            //加a并模10
            int newDigit = (digit + a) % 10;

            chars[i] = (char) (newDigit + '0');
        }
        //转回字符串
        return new String(chars);
    }

}
