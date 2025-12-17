package com.k4;

import java.util.Arrays;

public class Solution01 {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        char[] chars = colors.toCharArray();
        int countTime = 0;
        int i = 0;
        while (i < n) {
            int j = i;

            while (j + 1 < n && chars[j] == chars[j + 1]) {
                j++;
            }
            if (j - i + 1 > 1) {
                int[] ints = Arrays.copyOfRange(neededTime, i, j + 1);
                Arrays.sort(ints);
                for (int i1 = 0; i1 < j-i; i1++) {
                    countTime += ints[i1];
                }

            }
            i = j + 1;
        }
        return countTime;
    }
}
