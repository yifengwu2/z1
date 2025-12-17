package com.l;

import java.util.Arrays;

/**
 * 以知一个由小到大已排序好的数组，采用二分将一个输入插入到这个数组中，是插入数组仍然按由小到大的顺序排序
 */
public class Test04 {
    public static void main(String[] args) {
        int[] ans = {2, 5, 6, 7, 8, 12};
        int target = 3;
        int i = Test04.BinarySearch(ans, target);
        int[] res = new int[ans.length + 1];
        res[i] = target;
        int j = 0;
        for (int k = 0; k < res.length; k++) {
            if (k == i) continue;
            res[k] = ans[j++];
        }
        System.out.println(Arrays.toString(res));
    }

    public static int BinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
