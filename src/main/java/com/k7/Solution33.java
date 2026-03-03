package com.k7;

public class Solution33 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //行
        int row = matrix.length;
        //列
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            int[] mat = matrix[i];
            if (mat[0]>target){
                return false;
            }
            for (int j = 0; j < col; j++) {
                if (mat[j]==target){
                    return true;
                }
                if (mat[j]>target){
                    break;
                }
            }
        }
        return false;
    }
}
