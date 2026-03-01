package com.k7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution23 {
    public int[] minDistinctFreqPair(int[] nums) {
        int n = nums.length;
        if (n == 1 || n == 2) {
            return new int[]{-1, -1};
        }
        int[] res = new int[2];
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            Integer i = map.get(num);
            if (Objects.equals(i, map.get(nums[0]))) {

            } else {
                res[0] = nums[0];
                res[1] = num;
                break;
            }
        }
        if (res[0] == 0 && res[1] == 0) {
            return new int[]{-1, -1};
        }
        return res;

    }
}
