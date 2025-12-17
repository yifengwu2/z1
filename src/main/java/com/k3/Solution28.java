package com.k3;

public class Solution28 {
    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            int start = Math.max(0, j - nums[j]);
            cnt += s[j + 1] - s[start];
        }
        return cnt;
    }
}
