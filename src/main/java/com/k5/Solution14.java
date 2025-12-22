package com.k5;

public class Solution14 {
    public int minDeletionSize(String[] strs) {
        int row = strs.length;
        int col = strs[0].length();
        int[][] res = new int[row][col];
        if (row == 1) return 0;
        int cnt = 0;

        for (int i = 0; i < row; i++) {
            String s = strs[i];
            for (int j = 0; j < col; j++) {
                res[i][j] = s.charAt(j) - 'a';
            }
        }
        for (int j = 0; j < col; j++) {
            for (int i = 1; i < row; i++) {
                if (res[i][j] - res[i-1][j] < 0) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
}
