package com.f;

public class Solution02 {
    public int numberOfSubarrays(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);

    }

    private int solve(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int ans = 0;
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            if (num % 2 != 0) {
                count++;
            }
            while (count > k) {
                int num1 = nums[left];
                if (num1 % 2 != 0) {
                    count--;
                }
                left++;
            }
            ans= right - left + 1;

        }

        return ans;
    }
}
