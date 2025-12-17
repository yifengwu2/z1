package com.e;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {
    public int maximumUniqueSubarray(int[] nums) {
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int currentCount = 0;
        int maxCount = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            currentCount += num;

            while (map.get(num) == 2) {
                Integer value = map.get(nums[left]);
                value--;
                map.put(nums[left], value);
                currentCount -= nums[left];
                left++;
            }
            maxCount = Math.max(maxCount, currentCount);


        }
        return maxCount;
    }
}
