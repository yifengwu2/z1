package com.k6;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution27 {
    public int minStoneSum(int[] piles, int k) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int pile : piles) {
            pq.add(pile);
        }
        int cnt = 0;
        int j = 0;
        while (j<k) {
            Integer poll = pq.poll();
            double floor = Math.floor((double) poll / 2);
            double v = poll - floor;
            pq.offer((int) v);
            j++;
        }
        while (!pq.isEmpty()) {
            cnt += pq.poll();
        }
        return cnt;

    }
}
