package com.b;

import java.util.*;

public class Solution02 {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        //存值和索引
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int element = target - num;
            if (map.containsKey(element)) {
                list.add(map.get(element));
                list.add(i);
                break;
            }
            map.put(num, i);

        }
        int[] res = new int[2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
