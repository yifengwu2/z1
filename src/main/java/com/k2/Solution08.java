package com.k2;

public class Solution08 {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        if (n == 0) return 0;
        // 前缀和
        long[] arr = new long[nums.length + 1];
        long sum = 0;

        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + nums[i - 1];
            sum += nums[i - 1];
        }
        // 如果数组和已经能被p整除，直接返回0
        if (sum % p == 0) {
            return 0;
        }
        // i表示此轮遍历的子数组的长度
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j + i <= nums.length; j += 1) {
                if ((sum - (arr[j + i] - arr[j])) % p == 0) {
                    return i;
                }
            }
        }
        return -1;

    }
}
