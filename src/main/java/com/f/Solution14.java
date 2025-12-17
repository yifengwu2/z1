package com.f;

import java.util.Arrays;

public class Solution14 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int[] res = new int[n];
        int i = 0;
        int m = (n - 1) / 2;
        int mid = arr[m];
        while (left <= right) {
            if (Math.abs(arr[left] - mid) > Math.abs(arr[right] - mid)) {
                res[i++] = arr[left];
                left++;
            } else if (Math.abs(arr[left] - mid) < Math.abs(arr[right] - mid)) {
                res[i++] = arr[right];
                right--;
            } else {
                if (arr[left] > arr[right]) {
                    res[i++] = arr[left];
                    left++;
                } else {
                    res[i++] = arr[right];
                    right--;
                }
            }
        }
        int[] ans = new int[k];
        System.arraycopy(res, 0, ans, 0, k);

        return ans;
    }
}
