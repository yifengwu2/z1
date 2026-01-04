package com.k6;

import java.util.*;

public class Solution22 {
    public int lastStoneWeight(int[] stones) {
        //大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }
        while (pq.size() >= 2) {
            Integer y = pq.poll();
            Integer x = pq.poll();
            if (!Objects.equals(y, x)) {
                y -= x;
                pq.offer(y);
            }


        }
        return pq.isEmpty() ?0: pq.peek();


    }
}
