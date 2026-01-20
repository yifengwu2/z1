package com.k7;

import java.util.List;

public class Solution17 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            res[i] = findX(nums.get(i));
        }
        return res;
    }

    private int findX(int num) {
        for (int i = 0; i <= num; i++) {
            if ((i | i + 1) == num) {
                return i;
            }
        }
        return -1;
    }
}
