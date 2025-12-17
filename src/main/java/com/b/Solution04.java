package com.b;

import java.util.Arrays;

public class Solution04 {
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);// 步骤1：排序，便于从小到大取数
        int n = nums.length;

        int[] arr = new int[n];// 结果数组
        int left = 0;// 左指针，从小的数开始
        int right = n - 1;// 右指针，从大的数开始

        boolean flag = true;// 交替标志：true取left，false取right
        for (int i = 0; i < n; i++) {
            arr[i] = flag ? nums[left++] : nums[right--]; // 交替取数
            flag = !flag; // 切换标志
        }

        return arr;// 返回构造好的数组
    }
}
