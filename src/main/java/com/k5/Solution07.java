package com.k5;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {
    public int subarraySum(int[] nums, int k) {
        int prevSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cnt = 0;
        for (int num : nums) {
            prevSum += num;
            cnt += map.getOrDefault(prevSum - k, 0);
            map.put(prevSum, map.getOrDefault(prevSum, 0) + 1);
        }
        return cnt;
    }
}
