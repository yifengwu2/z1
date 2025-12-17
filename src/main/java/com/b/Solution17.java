package com.b;

import java.util.*;

public class Solution17 {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length();
        int n2 = second.length();

        if (Math.abs(n1 - n2) > 1) {
            return false;
        }

        //双指针比较
        int i = 0;
        int j = 0;
        int diffCount = 0;
        while (i < n1 && j < n2) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }

                //根据长度移动指针
                if (n1 > n2) {
                    i++;
                } else if (n1 < n2) {
                    j++;
                } else {
                    i++;
                    j++;
                }

            }
        }
        // 检查是否还有剩余字符（也算一次差异）
        return diffCount + (n1 - i) + (n2 - j) <= 1;
    }
}
