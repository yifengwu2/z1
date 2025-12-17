package com.k;

public class Solution14 {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int j = n - 1;
        int count0 = 0;
        int count1 = 0;
        boolean tag = false;

        int i = 0;
        for (i = 0; i < n; i++) {
            if (arr[i] == 0) {
                count0 += 2;
            } else {
                count1++;
            }
            if (count0 + count1 >= n) {
                // 判断最后一个零是不是只能写一个
                if (count0 + count1 > arr.length && arr[i] == 0) {
                    tag = true;
                }
                break;
            }
        }
        if (tag) {
            arr[j--] = 0;
            i--;
        }
        for (int k = i; k >= 0; k--) {
            if (arr[k] != 0) {
                arr[j--] = arr[k];
            } else {
                arr[j--] = 0;
                arr[j--] = 0;
            }
        }
    }
}
