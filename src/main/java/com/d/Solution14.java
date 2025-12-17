package com.d;

public class Solution14 {
    public int minOperations(int[] nums) {
        return minOps(nums, 0, nums.length - 1);
    }

    /**
     * 递归函数：计算区间 [left, right] 变为 0 所需的最少操作数
     */
    private int minOps(int[] nums, int left, int right) {
        //区间无效无需操作
        if (left > right) {
            return 0;
        }
        // 找到当前区间内的最小值（跳过 0）
        int minVal = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int num = nums[left];
            if (nums[i] != 0) {
                minVal = Math.min(minVal, num);
            }
        }
        // 如果整个区间都是 0，返回 0
        if (minVal == Integer.MAX_VALUE) {
            return 0;
        }

        //当前这个次操作：将所有min变为0
        int operations = 1;

        // 遍历区间，分割出各个非零子段进行递归
        int i = left;
        while (i < right) {
            if (nums[i] == minVal) {
                // 执行操作：设为 0
                nums[i] = 0;
                i++;
            } else if (nums[i] != 0) {
                // 找到一个连续的非零段
                int start = i;
                while (i < right && nums[i] != minVal && nums[i] != 0) {
                    i++;
                }
                int end = i - 1;
                operations += minOps(nums, start, end);
            } else {
                i++;// 当前是 0，跳过
            }
        }
        return operations;
    }


}
