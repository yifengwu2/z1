package com.k6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution19 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)){
                list.add(i);
            }
        }
        return list;

    }
}
