package com.k7;

public class Solution37 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left =mid+ 1;
            }
        }
        return (left < nums.length && nums[left] == target) ? left : -1;
    }
}
