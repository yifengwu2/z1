package com.b;

import java.util.HashMap;
import java.util.Map;

public class Solution23 {
    public int numberOfBeams(String[] bank) {
        int n = bank.length;
        int i = 0;
        int sum = 0;

        while (i < n) {
            int icount = 0;
            int jcount = 0;
            String s = bank[i];
            if (!s.contains("1")) {
                i++;
                continue;
            }
            int j = i + 1;

            while (j < n && !bank[j].contains("1")) {
                j++;
            }
            if (j >= n) {
                break;
            }

            //计算个数
            for (Character c : s.toCharArray()) {
                if (c == '1') {
                    icount++;
                }
            }
            for (Character c : bank[j].toCharArray()) {
                if (c == '1') {
                    jcount++;
                }
            }
            sum += icount * jcount;

            //最后
            i = j;


        }
        return sum;
    }
}
