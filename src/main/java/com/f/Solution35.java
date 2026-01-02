package com.f;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution35 {
    public int maximumProduct(int[] nums, int k) {
        long mod = 1_000_000_007L;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = pq.poll();
            int j = poll + 1;
            pq.offer(j);
        }
        long sum = 1;
        while (!pq.isEmpty()) {
            sum = (sum * pq.poll()) % mod;
        }
        return (int) sum;
    }
}
