package com.k6;

import java.util.ArrayDeque;
import java.util.Deque;

public class RecentCounter {
    private int count;
    private final Deque<Integer> deque;

    public RecentCounter() {
        this.count = 0;
        this.deque = new ArrayDeque<>(count);
    }

    public int ping(int t) {
        deque.offer(t);
        while (!deque.isEmpty() && deque.peekFirst() < t - 3000) {
            deque.poll();
        }
        return deque.size();
    }
}
