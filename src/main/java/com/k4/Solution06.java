package com.k4;

import java.util.Arrays;

public class Solution06 {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];

        if (k == 1) {
            for (int i = 0; i < res.length; i++) {
                res[i] = nums[i];
            }
            return res;
        }
        Arrays.fill(res,-1);

        int i = 0;
        int right = 0;
        while (i < n - k + 1) {
            int j = Math.max(i, right);
            while (j + 1 < n && nums[j + 1] - nums[j] == 1) {
                    j++;
                if (j - i + 1 == k) {
                    break;
                }
            }
            if (j - i + 1 == k) {
                res[i] = nums[j];
            }
            right = j;

            i++;
        }
        return res;
    }
}
