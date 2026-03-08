package com.k7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution47 {
    public int[] rotateElements(int[] nums, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                list.add(nums[i]);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return nums;

    }
}
