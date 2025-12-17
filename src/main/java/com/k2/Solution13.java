package com.k2;

import java.util.*;

public class Solution13 {
    private static final double MOD = 1e+7;

    public int countTrapezoids(int[][] points) {
        // 1. 按 y 坐标分组，并在每组内按 x 坐标排序
        Map<Integer, List<Integer>> yToXs = new HashMap<>();
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            yToXs.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // 2. 将 y 坐标排序，以便按顺序处理
        List<Integer> ys = new ArrayList<>(yToXs.keySet());
        Collections.sort(ys);

        long result = 0;

        // 3. 枚举所有可能的上下底组合 (y1 < y2)
        for (int i = 0; i < ys.size(); i++) {
            for (int j = i + 1; j < ys.size(); j++) {
                int y1 = ys.get(i);
                int y2 = ys.get(j);

                List<Integer> xs1 = yToXs.get(y1);
                List<Integer> xs2 = yToXs.get(y2);

                // 确保每行至少有两个点才能形成线段
                if (xs1.size() < 2 || xs2.size() < 2) {
                    continue;
                }

                // 计算 C(n, 2) = n * (n - 1) / 2
                long count1 = ((long) xs1.size() * (xs1.size() - 1)) / 2;
                long count2 = ((long) xs2.size() * (xs2.size() - 1)) / 2;

                // 累加上下底组合的数量
                result = (long) ((result + (count1 * count2) % MOD) % MOD);
            }
        }

        return (int) result;



    }
}
