package com.a;

public class Solution04 {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            //比较nums[mid]和nums[mid+1]
            if (nums[mid] < nums[mid + 1]) {
                // 上升趋势 → 峰值在右半部分（不包括 mid）
                left = mid + 1;
            } else {
                // 下降趋势 → 峰值在左半部分（包括 mid）
                right = mid;
            }

        }

        return left;// 或 return right，此时 left == right
    }


}
