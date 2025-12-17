package com.c;

import java.util.HashMap;
import java.util.Map;

public class Solution08 {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        //遍历每一行
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(",");
            }
            String rowKey = sb.toString();
            map.put(rowKey, map.getOrDefault(rowKey, 0) + 1);
        }
        //遍历每一列
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append(",");//固定j遍历i
            }
            String colKey = sb.toString();
            count += map.getOrDefault(colKey, 0);
        }

        return count;
    }
}
