package com.a;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution13 {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }
        int expect = k;
        for (int i = 0; i < n; i++) {
            if (set.contains(expect)) {
                expect += k;
            }else {
                return expect;
            }
        }
        return expect;
    }
}
