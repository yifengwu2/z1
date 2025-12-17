package com.k2;

public class Solution04 {
    public int maxDistance(int[] nums1, int[] nums2) {
//        int maxLen = 0;
//        for (int i = 0; i < nums1.length; i++) {
//            for (int j = i; j < nums2.length; j++) {
//                if (nums1[i] <= nums2[j]) {
//                    maxLen = Math.max(maxLen, j - i);
//                }
//            }
//        }
//        return maxLen;
        int maxLen = 0;
        int j = 0;
        int n = nums1.length;
        int n2 = nums2.length;
        for (int i = 0; i < n; i++) {
            j = Math.max(j, i);
            while (j < n2 && nums2[j] >= nums1[i]) {
                maxLen = Math.max(maxLen, j - i);
                j++;
            }
        }
        return maxLen;
    }
}
