package com.d;

import java.util.HashSet;
import java.util.Set;

public class Solution21 {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;

        if (n < k) return 0;


        int left = 0;
        long maxSum = 0;
        // 当前窗口的和
        long currentSum = 0;
        //当前窗口中的数字集合
        Set<Integer> window = new HashSet<>();

        for (int right = 0; right < n; right++) {
            //当前要加入的数
            int num = nums[right];

            while (window.contains(num)) {
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
            // 安全地把 nums[right] 加进窗口
            window.add(nums[right]);
            currentSum += nums[right];


            //窗口满了（正好k个元素）
            if (right - left + 1 == k) {
                // 更新最大值
                maxSum = Math.max(maxSum, currentSum);

                // 滑动窗口：准备下一轮，移除最左边的元素
                window.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }
}
