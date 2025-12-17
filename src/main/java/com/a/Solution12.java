package com.a;

import java.util.*;

public class Solution12 {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {

            Set<Integer> ji = new HashSet<>();
            Set<Integer> ou = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) {
                    ou.add(nums[j]);
                } else {
                    ji.add(nums[j]);
                }

                if (ji.size() == ou.size()) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }


        }
        return maxLength;
    }
}
