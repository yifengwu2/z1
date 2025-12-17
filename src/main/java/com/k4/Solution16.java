package com.k4;

import java.util.*;

public class Solution16 {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //值，频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int prev = target - num;
            Integer prevCount = map.getOrDefault(prev, 0);

            if (prevCount> 0) {
                List<Integer> list = new ArrayList<>();
                list.add(prev);
                list.add(num);
                res.add(list);
                map.put(prev, prevCount - 1);
            }else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

        }
        return res;
    }
}
