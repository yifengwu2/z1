package com.c;

import java.util.*;

public class Solution19 {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        List<Integer> list = new ArrayList<>();

        int max = nums[n - 1];

        int firs = nums[0];

        for (int i = firs; i < max; i++) {
            if (set.contains(firs)) {
                firs++;
            } else {
                list.add(firs++);
            }

        }


        return list;
    }
}
