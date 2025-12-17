package com.d;

import java.util.HashSet;
import java.util.Set;

public class Solution20 {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;

        int left = 0;

        long maxSum = 0;

        for (int right = 0; right < n; right++) {

            // 维护窗口大小不超过 k
            if (right - left + 1 > k) {
                left++;

            }
            // 遍历窗口内所有元素 [left, right]
            if (right - left + 1 == k) {
                long sum = 0;
                Set<Integer> set = new HashSet<>();
                for (int i = left; i <= right; i++) {
                    if (!set.contains(nums[i])) {
                        set.add(nums[i]);
                        sum += nums[i];
                    } else {
                        sum = 0;// 发现重复，清零并跳出
                        break;
                    }

                }
                if (sum != 0) {
                    maxSum = Math.max(maxSum, sum);
                }
            }

        }
        return maxSum;
    }
}
