package com.e;

import java.util.Arrays;

public class Solution14 {
    public int maximizeExpressionOfThree(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int num = nums[n - 1];
        int num1 = nums[n - 2];
        int num2 = nums[0];
        return num + num1 - num2;

    }
}
