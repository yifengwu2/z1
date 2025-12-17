package com.d;

public class Solution15 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        return Options(nums, 0, n - 1);
    }

    private int Options(int[] nums, int left, int right) {

        if (left > right) {
            return 0;
        }
        //找到范围中最小值
        int minVal = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            if (nums[i] != 0) {
                minVal = Math.min(minVal, nums[i]);
            }
        }
        //表示这个范围全部是0
        if (minVal == Integer.MAX_VALUE) {
            return 0;
        }
        int options = 1;

        int i = left;
        while (i <= right) {
            //找到最小值后先赋值为0
            if (nums[i] == minVal) {
                nums[i] = 0;
                i++;
            } else if (nums[i] != 0) {
                int start = i;
                // 找到一个连续的非零段
                while (i < right && nums[i] != minVal && nums[i] != 0) {
                    i++;
                }
                int end = i - 1;
                options += Options(nums, start, end);
            } else {
                i++;
            }
        }
        return options;
    }

}
