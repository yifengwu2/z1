package com.f;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution33 {
    public int halveArray(int[] nums) {
        long sum = 0;
        Queue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            sum += num;
            pq.offer((double) num);
        }
        double db = sum / 2.0;
        double num = 0;
        long cnt = 0;
        double v = 0;
        while (v < db) {
            double poll = pq.poll();
            double i = poll / 2.0;
            num += i;
            pq.offer(i);
            cnt++;
            v = sum - num;
        }
        return (int) cnt;
    }
}
