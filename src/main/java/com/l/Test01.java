package com.l;

import java.util.Arrays;

/**
 * 1.编写程序讲一个数组元素逆序存放
 */
public class Test01 {
    public static int[] reverse(int[] nums) {
        int n = nums.length;
//        int[] ans = new int[n];
//        int k = 0;
//        for (int i = n - 1; i >= 0; i--) {
//            ans[k++] = nums[i];
//        }
//        System.out.println(Arrays.toString(ans));
        int left = 0;
        int right = n - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] ans = {7, 4, 6, 2, 0, 5};
        System.out.println(Arrays.toString(Test01.reverse(ans)));

    }
}
