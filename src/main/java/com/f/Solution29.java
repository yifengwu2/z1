package com.f;

public class Solution29 {
    public int countNegatives(int[][] grid) {
        int cnt = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int[] ints : grid) {
            for (int j = 0; j < col; j++) {
                int num = ints[j];
                if (num < 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
