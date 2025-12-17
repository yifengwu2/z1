package com.k;

public class Solution13 {
    public int[] transformArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int slow = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                ans[slow] = 0;
                slow++;
            }
        }

        for (int j = slow; j < n; j++) {
            ans[j] = 1;
        }
        return ans;
    }
}
