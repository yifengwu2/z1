package com.k6;

import java.util.*;

public class Solution23 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        Queue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i1[1] - i2[1];
            }
            return i1[0] - i2[0];
        });
        for (int i = 0; i < nums.length; i++) {
            pq.add(new int[]{nums[i], i});
        }
        for (int i = 0; i < k; i++) {
            int[] poll = pq.poll();
            poll[0] *= multiplier;
            nums[poll[1]] = poll[0];
            pq.add(poll);
        }
        return nums;

    }
}
