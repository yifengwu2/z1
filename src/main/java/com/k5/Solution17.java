package com.k5;

import java.util.*;

public class Solution17 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int start = 0;
        int cnt = 0;

        while (start < n) {
            Set<Integer> set = new HashSet<>();
            boolean unique = true;
            for (int i = start; i < n; i++) {
                if (!set.add(nums[i])) {
                    unique = false;
                    break;
                }
            }

            if (unique) break;

            start += 3;
            cnt++;
        }

        return cnt;

    }
}
