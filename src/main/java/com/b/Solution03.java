package com.b;

import java.util.HashMap;
import java.util.Map;

public class Solution03 {
    public int[] arrayChange(int[] nums, int[][] operations) {
        //值和索引
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int[] ints : operations) {
            int anInt = ints[0];
            if (map.containsKey(anInt)) {
                Integer index = map.get(anInt);
                nums[index] = ints[1];
                map.put(ints[1],index);

            }
        }
        return nums;
    }
}
