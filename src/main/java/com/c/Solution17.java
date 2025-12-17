package com.c;

public class Solution17 {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        if (guards == null || guards.length == 0) return m * n - walls.length;

        //0表示没被守卫，2表示被占用，1表示被守卫
        //网格
        int[][] grid = new int[m][n];

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            int[] re = grid[i];
            for (int j = 0; j < re.length; j++) {
                //全部初始化为0；
                grid[i][j] = 0;
            }
        }

        for (int[] wall : walls) {
            int row = wall[0];
            int col = wall[1];

            grid[row][col] = 2;
        }
        for (int[] guard : guards) {
            int row = guard[0];
            int col = guard[1];
            grid[row][col] = 2;
        }
        //四个方向
        //左右下上
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for (int[] guard : guards) {
            int row = guard[0];
            int col = guard[1];
            for (int[] r : dir) {
                int i = r[0];
                int j = r[1];

                int k = row + i;
                int l = col + j;
                while (k >= 0 && k < m && l >= 0 && l < n && grid[k][l] != 2) {
                    //标记
                    grid[k][l] = 1;
                    k += i;
                    l += j;

                }

            }
        }
        for (int[] res : grid) {
            for (int re : res) {
                if (re == 0) {
                    count++;
                }
            }
        }

        return count;
    }

}
