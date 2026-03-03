package com.k7;

public class Solution32 {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int index = i + 1;
                while (index < n && nums[index] == 0) {
                    index++;
                }
                if (index < n) {
                    nums[i] = nums[index];
                    nums[index] = 0;
                }

            }
        }
    }
}
