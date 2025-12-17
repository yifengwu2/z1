package com.e;

import java.util.HashMap;
import java.util.Map;

public class Solution08 {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < n; right++) {
            int num = nums[right];
            map.put(num, map.getOrDefault(num, 0) + 1);

            while (map.get(num) > k) {
                Integer value = map.get(nums[left]);
                value--;
                map.put(nums[left], value);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
