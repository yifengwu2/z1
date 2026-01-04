package com.k6;

import java.util.*;

public class Solution24 {
    public long pickGifts(int[] gifts, int k) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int gift : gifts) {
            pq.add(gift);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = pq.poll();
            Integer sqrt = (int) Math.sqrt(poll);
            pq.add(sqrt);
        }
        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }
}
