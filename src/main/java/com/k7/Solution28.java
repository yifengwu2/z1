package com.k7;

public class Solution28 {
    public int dominantIndices(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        long sum = 0;
        int cnt = 0;
        for (int num : nums) {
            sum += num;
            cnt++;
        }
        long sumRight = sum;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int rightLen = cnt - 1 - i;
            sumRight -= nums[i];
            if ((long) nums[i] * rightLen > sumRight) {
                count++;
            }
        }
        return count;
    }
}
