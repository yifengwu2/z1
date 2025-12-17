package com.k3;

public class Solution06 {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int count = 0;
        int j = 0;
        for (int i : nums) {
            count += i;
        }
        int k = 0;
        for (int i = 0; i < n - 1; i++) {
            k += nums[i];
            int num = count - k;
            if ((k - num) % 2 == 0) {
                j++;
            }
        }
        return j;
    }
}
