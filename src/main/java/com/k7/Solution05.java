package com.k7;

import java.util.ArrayList;
import java.util.List;

public class Solution05 {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        List<int[]> list = new ArrayList<>();
        int n = towers.length;
        for (int i = 0; i < n; i++) {
            int[] tower = towers[i];
            int distance = Math.abs(tower[0] - center[0]) + Math.abs(tower[1] - center[1]);
            if (distance <= radius) {
                list.add(tower);
            }
        }
        if (list.isEmpty()) {
            return new int[]{-1, -1};
        }
        list.sort((a, b) -> {
            if (a[2] != b[2]) {
                return b[2] - a[2];
            } else {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });
        int[] first = list.getFirst();
        return new int[]{first[0], first[1]};
    }
}
