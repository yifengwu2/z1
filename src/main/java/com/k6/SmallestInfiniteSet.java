package com.k6;

import java.util.PriorityQueue;
import java.util.Queue;

public class SmallestInfiniteSet {
    private final Queue<Integer> pq;

    public SmallestInfiniteSet() {
        this.pq = new PriorityQueue<>();
        for (int i = 1; i <= 1000; i++) {
            pq.add(i);
        }
    }

    public int popSmallest() {
        return pq.poll();
    }

    public void addBack(int num) {
        if (!pq.contains(num)) {
            pq.add(num);
        }
    }
}
