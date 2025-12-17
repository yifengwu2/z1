package com.d;

public class Solution10 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int left = 0;

        int count = 0;
        int sum = 0;
        for (int right = 0; right < n; right++) {
            int a = arr[right];
            sum += a;

            if (right - left + 1 > k) {
                sum -= arr[left];
                left++;
            }

            if (right - left + 1 == k && sum >= threshold * k) {
                count++;
            }
        }
        return count;
    }
}
