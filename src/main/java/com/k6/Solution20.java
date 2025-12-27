package com.k6;

import java.util.ArrayList;
import java.util.List;

public class Solution20 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        int[] ans = new int[n + 1];
        for (int num : nums) ans[num]++;
        for (int i = 1; i < n + 1; i++) {
            if (ans[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }
}
