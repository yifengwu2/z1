package com.k2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution06 {
    public int countElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int num : nums) {
            int i = BinarySearch(nums, num);
            if (n - i > k) {
                count++;
            }
        }
        return count;
    }

    // 辅助函数：找第一个 > target 的下标
    private int BinarySearch(int[] nums, int target) {
        int right = nums.length;
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
