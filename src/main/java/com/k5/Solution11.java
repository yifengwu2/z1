package com.k5;

import java.util.HashMap;
import java.util.Map;

public class Solution11 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            prefix += nums[i];
            if (!map.containsKey(prefix)) {
                map.put(prefix, i);
            }else {
                Integer j = map.get(prefix);
                maxLen = Math.max(maxLen, i - j);
            }
        }
        return maxLen;
    }
}
