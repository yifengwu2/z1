package com.k5;

import java.util.HashMap;
import java.util.Map;

public class Solution09 {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            if (nums[0] % k != 0) {
                return 0;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prev = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            prev += nums[i];
            int mod = (prev % k + k) % k;
            cnt += map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return cnt;
    }
}
