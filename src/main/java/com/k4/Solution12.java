package com.k4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution12 {
    public int maxOperations(int[] nums, int k) {
        int n = nums.length;
        //索引，值
        Map<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int pre = k - nums[i];
            if (map1.containsValue(pre)) {
                for (Integer integer : map1.keySet()) {
                    if (map1.get(integer) == pre) {
                        map1.remove(integer);
                        break;
                    }
                }
                count++;
            } else {
                map1.put(i, nums[i]);
            }
        }
        return count;
    }
}
