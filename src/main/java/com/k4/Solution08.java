package com.k4;

import java.util.ArrayList;
import java.util.List;

public class Solution08 {
    public int findMaxK(int[] nums) {
        int maxNum = -1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(-nums[i])) {
                if (nums[i]<0) {
                    maxNum = Math.max(maxNum, nums[i]);
                }else {
                    maxNum = Math.max(maxNum, -nums[i]);
                }
            }
            list.add(nums[i]);
        }
        return maxNum;
    }
}
