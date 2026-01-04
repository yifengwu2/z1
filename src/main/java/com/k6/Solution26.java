package com.k6;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution26 {
    public int minOperations(int[] nums, int k) {
        Queue<Long> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add((long) num);
        }
        int cnt = 0;
        while (!pq.isEmpty() && pq.peek() < k) {
            Long x = pq.poll();
            Long y = pq.poll();
            pq.add(Math.min(x, y) * 2 + Math.max(x, y));
            cnt++;
        }
        return cnt;

    }
}
