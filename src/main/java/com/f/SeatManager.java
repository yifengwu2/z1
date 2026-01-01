package com.f;

import java.util.PriorityQueue;
import java.util.Queue;

public class SeatManager {
    private final Queue<Integer> pq = new PriorityQueue<>();

    public SeatManager(int n) {
        for (int i = 1; i <= n; i++) {
            pq.offer(i);
        }

    }

    public int reserve() {
        if (!pq.isEmpty()) {
            return pq.poll();
        }
        return 0;
    }

    public void unreserve(int seatNumber) {
        pq.offer(seatNumber);
    }
}
