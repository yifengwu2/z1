package com.k4;

import java.util.*;

public class Solution20 {
    public int maxSum(int[] nums) {
        int n = nums.length;
        int maxSum = -1;
//        //索引，数位最大数字
//        Map<Integer, Integer> map = new HashMap<>();
        //数字，索引
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];

            int max = getMaxDigital(num);

            if (map1.containsKey(max)) {
                Integer index = map1.get(max);
                int sum = nums[index] + num;
                maxSum = Math.max(maxSum, sum);
                if (nums[index] < num) {
//                    map.put(i, max);
                    map1.put(max, i);
                }
            } else {
//                map.put(i, max);
                map1.put(max, i);
            }
        }
        return maxSum;
    }

    private int getMaxDigital(int num) {
        int res = 0;
        while (num != 0) {
            res = Math.max(res, num % 10);
            num /= 10;
        }
        return res;
    }
}
