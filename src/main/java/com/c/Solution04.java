package com.c;

import java.util.Arrays;

public class Solution04 {
    public int minimumCost(int[] cost) {
        int n = cost.length;

        Arrays.sort(cost);

        int sum = 0;

        for (int i = n - 1; i >= 0; i -= 3) {
            int m1 = cost[i];
            sum += m1;
            if (i - 1 >= 0) {
                int m2 = cost[i - 1];
                sum += m2;
            }

        }
        return sum;
    }
}
