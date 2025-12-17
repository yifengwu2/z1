package com.k;

public class Solution19 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //三个指针
        int i = m - 1;// nums1 有效部分的末尾
        int j = n - 1;//num2的末尾
        int k = m + n - 1;//合并后数组的末尾

        //从后往前合并
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        // 处理剩余元素
        // 如果 nums1 还有剩余，它们已经在正确位置，无需处理
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
