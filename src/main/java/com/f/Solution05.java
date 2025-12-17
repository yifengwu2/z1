package com.f;

public class Solution05 {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        //按列遍历
        for (int col = y; col < y + k; col++) {
            int left = x;
            int right = x + k - 1;

            //定列，控制行
            while (left < right) {
                int num = grid[left][col];
                grid[left][col] = grid[right][col];
                grid[right][col] = num;
                left++;
                right--;
            }

        }
        return grid;
    }
}
