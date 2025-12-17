package com.d;

import java.util.HashSet;
import java.util.Set;

public class Solution22 {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;

        int left = 0;
        if (n < k) return 0;

        Set<Integer> window = new HashSet<>();
        //最大的和
        int maxSum = 0;
        //当前窗口的和
        int currentSum = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            while (window.contains(num)) {
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            window.add(nums[right]);
            currentSum += nums[right];

            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);

                //准备下一个数
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }


        }
        return maxSum;
    }
}
