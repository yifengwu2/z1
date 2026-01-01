package com.f;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargest {
    private final Queue<Integer> pq;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>((a, b) -> a - b);
        this.k = k;
        for (int num : nums) {
            pq.offer(num);
        }
    }

    public int add(int val) {
        pq.offer(val);
        while (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
