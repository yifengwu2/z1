package com.k5;

import java.util.HashMap;
import java.util.Map;

public class Solution05 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
//        int[] s = new int[n + 1];
//        for (int i = 0; i < n; i++) {
//            s[i + 1] = s[i] + nums[i];
//        }

        int cnt = 0;
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1); // s[0] = 0，必须先放！
        int prev = 0;

        for (int r = 0; r < n; r++) {
//            for (int l = 0; l <= r; l++) {
//                if (s[r + 1] - s[l] == goal) {
//                    cnt++;
//                }
//            }
            prev += nums[r];
            cnt += count.getOrDefault(prev-goal, 0);
            count.put(prev, count.getOrDefault(prev, 0) + 1);

        }
        return cnt;

    }
}
