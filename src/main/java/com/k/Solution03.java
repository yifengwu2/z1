package com.k;

import java.util.Arrays;

public class Solution03 {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }

        return left ;
    }
}
