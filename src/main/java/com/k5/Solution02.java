package com.k5;

public class Solution02 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = queries.length;
        boolean[] booleans = new boolean[n];
        int[] ans = new int[nums.length];
        //偶为1，奇为-1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                ans[i] = 1;
            } else {
                ans[i] = -1;
            }
        }
        int[] s = new int[nums.length + 1];
        s[0] = 1;
        for (int i = 0; i < ans.length; i++) {
            s[i + 1] = s[i] * ans[i];
            if (s[i+1]-s[i]!=0||s[i+1]-s[i]!=-1||s[i+1]-s[i]!=1){
                s[i+1]=2;
            }
        }
        for (int j = 0; j < queries.length; j++) {
            int[] query = queries[j];
            int left = query[0];
            int right = query[1];
            if (nums[left] % 2 != 0) {
                int i = s[right + 1] - s[left];
                if (i == -1) {
                    booleans[j] = true;
                }
            }else {
                int i = s[right + 1] - s[left];
                if (i == 1) {
                    booleans[j] = true;
                }

            }

        }
        return booleans;
    }
}
