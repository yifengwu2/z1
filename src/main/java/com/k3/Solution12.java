package com.k3;

import java.util.Map;

public class Solution12 {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        int up = 0;
        int down = 0;
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                up++;
                down = 0;
            } else if (arr[i] < arr[i - 1]) {
                down++;
                if (up > 0) {
                    maxLen = Math.max(maxLen, up + down + 1);
                }
            } else {
                up = 0;
                down = 0;

            }
        }
        return maxLen;
    }
}
