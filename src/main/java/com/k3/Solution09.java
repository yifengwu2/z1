package com.k3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution09 {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0];
        int i = 0;
        int count = 0;
        int rank = 0;
        while (i < n) {
            if (nums[i] == min) {
                i++;
                continue;
            }
            int j = i;
            while (j + 1 < n && nums[j] == nums[j + 1]) {
                j++;
            }
            rank++;
            int len = j - i + 1;
            count += rank * len;

            i = j + 1;
        }
        return count;

    }
}
