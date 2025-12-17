package com.d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution17 {
    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();

        int left = 0;

        long MaxSum = 0;
        for (int right = 0; right < n; right++) {

            while (right - left + 1 > k) {
                left++;
            }

            long sum = 0;
            if (right - left + 1 == k) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = left; i <= right; i++) {
                    map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
                    //优化
                    sum += nums.get(i);
                }

                if (map.size() >= m) {
//                    for (int i = left; i <= right; i++) {
//                        sum += nums.get(i);
//                    }

                } else {
                    sum = 0;
                }
            }
            MaxSum = Math.max(MaxSum, sum);

        }

        return MaxSum;
    }
}
