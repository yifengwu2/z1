package com.c;

import java.util.Arrays;

public class Solution33 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        if (arr.length == 1) {
            arr[0] = 1;
            return arr[0];
        }
        int n = arr.length;
        Arrays.sort(arr);
        int max = 0;
        if (arr[0] != 1) {
            arr[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            int num1 = arr[i];
            int num2 = arr[i - 1];
            if (Math.abs(num1 - num2) <= 1) {

            } else {
                arr[i] = arr[i - 1] + 1;
            }

        }
        for (Integer a : arr) {
            max = Math.max(max, a);
        }

        return max;
    }
}
