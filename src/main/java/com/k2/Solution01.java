package com.k2;

import java.util.Arrays;

public class Solution01 {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int MOD = 1000000007;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        long count = 0;

        int j = drinks.length - 1;
        for (int k : staple) {
            while (j >= 0 && k + drinks[j] > x) {
                j--;
            }
            count += j + 1;
        }
        return (int)count % MOD;
    }
}
