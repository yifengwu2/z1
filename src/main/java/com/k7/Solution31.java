package com.k7;

public class Solution31 {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0;
        while (left < n - 1) {
            if (nums[left]==0) {
                int right = left + 1;
                while (right < n && nums[right] == 0) {
                    right++;
                }
                if (right<n){
                nums[left] = nums[right];
                nums[right] = 0;
                }
            }else {
                left++;
            }
        }
    }
}
