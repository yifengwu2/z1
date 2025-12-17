package com.k;

import java.util.HashSet;
import java.util.Set;

public class Solution21 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Set<Integer> set = new HashSet<>();
        for (int[] ans : nums1) {
            set.add(ans[0]);
        }
        for (int[] ans : nums2) {
            set.add(ans[0]);
        }
        int n = set.size();
        int[][] res = new int[n][2];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i][0] == nums2[j][0]) {
                int num = nums1[i][1] + nums2[j][1];
                res[k][0] = nums1[i][0];
                res[k][1] = num;
                i++;
                j++;
                k++;
            } else if (nums1[i][0] < nums2[j][0]) {
                res[k][0] = nums1[i][0];
                res[k][1] = nums1[i][1];
                i++;
                k++;
            } else {
                res[k][0] = nums2[j][0];
                res[k][1] = nums2[j][1];
                j++;
                k++;
            }
        }
        while (i < n1) {
            res[k][0] = nums1[i][0];
            res[k][1] = nums1[i][1];
            i++;
            k++;
        }
        while (j < n2) {
            res[k][0] = nums2[j][0];
            res[k][1] = nums2[j][1];
            j++;
            k++;
        }
        return res;

    }
}
