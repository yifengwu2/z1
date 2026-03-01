package com.k7;

public class Solution24 {
    public int scoreDifference(int[] nums) {
        int n = nums.length;
        int s1 = 0;
        int s2 = 0;
        boolean b1 = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num % 2 != 0) {
                b1 = !b1;
            }
            if ((i + 1) % 6 == 0) {
                b1 = !b1;
            }

            if (b1) {
                s1 += num;
            }else {
                s2 += num;
            }
        }
        return s1 - s2;
    }
}
