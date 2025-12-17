package com.c;

import java.util.*;

public class Solution28 {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        //初始化并查集，并合并所有连接
        UnionFind uf = new UnionFind(c + 1);
        for (int[] conn : connections) {
            uf.union(conn[0], conn[1]);
        }
        // 记录每个电站是否在线
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        Map<Integer, PriorityQueue<Integer>> comToHeap = new HashMap<>();
        for (int i = 0; i <= c; i++) {
            int root = uf.find(i);
            comToHeap.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];
            if (type == 2) {
                online[x] = false;
            } else if (type == 1) {
                //：如果 x 本身在线 → 返回 x 自己
                if (online[x]) {
                    res.add(x);
                } else {
                    //如果 x 已离线 → 找同电网中最小的在线电站
                    int root = uf.find(x); // 找到 x 所在电网的根
                    PriorityQueue<Integer> pq = comToHeap.get(root);// 获取该电网的堆
                    // 懒删除：清理堆顶的离线电站
                    while (pq != null && !pq.isEmpty() && !online[pq.peek()]) {
                        pq.poll();
                    }
                    // 返回结果
                    if (pq != null && pq.isEmpty()) {
                        res.add(pq.peek());
                    } else {
                        res.add(-1);
                    }
                }
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}

class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);//路径压缩
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            parent[rx] = ry;
        }
    }
}
