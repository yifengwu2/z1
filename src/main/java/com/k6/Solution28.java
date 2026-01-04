package com.k6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution28 {
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] res = new int[n];
        int[] ans = new int[n];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int num = Math.abs(query[0]) + Math.abs(query[1]);
            ans[i] = num;
        }
        //大顶堆
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            pq.offer(ans[i]);
            if (pq.size() < k) {
                res[i] = -1;
            } else if (pq.size() == k) {
                res[i] = pq.peek();
            } else {
                pq.poll();
                res[i] = pq.peek();
            }
        }
        return res;

    }
}
