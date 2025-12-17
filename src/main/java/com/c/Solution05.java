package com.c;

import java.util.ArrayList;
import java.util.List;

public class Solution05 {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int leftSum = 0;
        int rightSum = 0;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                list.add(i);
            }
        }
        int count = 0;

        for (int index : list) {
            for (int i = index - 1; i >= 0; i--) {
                leftSum += nums[i];
            }
            for (int i = index + 1; i < n; i++) {
                rightSum += nums[i];
            }
            if (leftSum - rightSum == 0) {
                count += 2;
            } else if (Math.abs(leftSum - rightSum) == 1) {
                count++;
            } else {
                count += 0;
            }
            leftSum = 0;
            rightSum = 0;
        }


        return count;
    }
}
