package com.d;

import java.util.Arrays;

public class Solution16 {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int countW = 0;
        int countB = 0;
        int left = 0;
        int minOp = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            char c = blocks.charAt(right);
            if (c == 'W') {
                countW++;
            } else {
                countB++;
            }
            while (countW + countB > k) {
                char c1 = blocks.charAt(left);
                if (c1 == 'W') {
                    countW--;
                } else {
                    countB--;
                }
                left++;
            }
            if (countW + countB == k) {
                minOp = Math.min(minOp, countW);
            }
        }
        return minOp;
    }
}

