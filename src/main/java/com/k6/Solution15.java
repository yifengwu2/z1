package com.k6;

public class Solution15 {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n * 2];
        int[] res1 = new int[n];
        int[] res2 = new int[n];
        System.arraycopy(nums, 0, res1, 0, n);
        int j = 0;
        for (int i = n; i < 2 * n; i++) {
            res2[j] = nums[i];
            j++;
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            ans[k++] = res1[i];
            ans[k++] = res2[i];
        }
        return ans;


    }
}
