package com.f;

import java.util.*;

public class Solution36 {
    public int smallestChair(int[][] times, int targetFriend) {
//        int maxTime = 0;
//        for (int[] time : times) {
//            maxTime = Math.max(maxTime, time[1]);
//        }
//        //到达时间->座位
//        Map<Integer, Integer> map = new HashMap<>();
//        //到达时间->朋友
//        Map<Integer, Integer> map1 = new TreeMap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        for (int i = 0; i < times.length; i++) {
//            map1.put(times[i][0], i);
//        }
//        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[1] - o2[1];
//            }
//        });
//        int n = times.length;
//        //椅子
//        int c = 0;
//
//        for (int i = 0; i < maxTime; i++) {
//            int[] peek = pq.peek();
//            if (peek != null && peek[1] == i) {
//                int[] poll = pq.poll();
//                int c1 = poll[0];
//                if (map1.get(i) != null) {
//                    map.put(map1.get(), c1);
//                    continue;
//                }
//            }
//            map.put(i, c);
//            pq.offer(new int[]{c, times[i][1]});
//            c++;
//        }
//        return map.get(targetFriend);
        return 0;
    }
}
