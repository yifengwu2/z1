package com.k6;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution25 {
    public long maxKelements(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            queue.add(num);
        }
        long sum = 0;
        for (int i = 0; i < k; i++) {
            Integer poll = queue.poll();
            sum += poll;
            double num = (double) poll / 3;
            num = Math.ceil(num);
            queue.add((int) num);
        }
        return sum;
    }
}
