package com.k4;

import java.util.HashMap;
import java.util.Map;

public class Solution15 {
    public int maximumSum(int[] nums) {
        int n = nums.length;
        //数位和，值
        Map<Integer, Integer> map = new HashMap<>();
        int maxNum = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int num = nums[i];
            int x = num;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (map.containsKey(sum)) {
                Integer value = map.get(sum);
                maxNum = Math.max(maxNum, value + num);
            }
            map.put(sum, Math.max(map.getOrDefault(sum,0),num));
        }
        return maxNum == 0 ? -1 : maxNum;
    }
}
