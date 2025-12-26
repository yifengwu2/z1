package com.k6;

import java.util.Arrays;

public class Solution03 {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        int j = 0;
        long sum = 0;
        for (int i = n - 1; i > n - k; i--) {
            int num = happiness[i] - j;
            if (num >= 0) {
                sum += num;
            }
            j++;
        }
        return sum;
    }

}
