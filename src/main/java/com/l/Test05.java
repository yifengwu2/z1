package com.l;

import java.util.Arrays;

/**
 * 编写程序实现矩阵的加法，减法，乘法运算
 */
public class Test05 {
    /**
     * 矩阵加法
     *
     * @param ans1 矩阵1
     * @param ans2 矩阵2
     */
    public static void addition(int[][] ans1, int[][] ans2) {
        int row = ans1.length;
        int col = ans1[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int num = ans1[i][j] + ans2[i][j];
                res[i][j] = num;
            }
            System.out.println(Arrays.toString(res[i]));
        }
    }

    /**
     * 减法
     *
     * @param ans1 矩阵1
     * @param ans2 矩阵2
     */
    public static void subtraction(int[][] ans1, int[][] ans2) {
        int row = ans1.length;
        int col = ans1[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int num = ans1[i][j] - ans2[i][j];
                res[i][j] = num;
            }
            System.out.println(Arrays.toString(res[i]));
        }
    }

    /**
     * 乘法
     */
    public static void multiplication(int[][] ans1, int[][] ans2) {
        int row = ans1.length;
        int col = ans1[0].length;
        int p = ans2[0].length;
        int[][] ints = new int[row][p];

        for (int i = 0; i < row; i++) {
            for (int k = 0; k < p; k++) {
                int sum = 0;
                for (int j = 0; j < col; j++) {
                    sum += ans1[i][j] * ans2[j][k];
                }
                ints[i][k] = sum;
            }
            System.out.println(Arrays.toString(ints[i]));

        }

    }


    public static void main(String[] args) {
        int[][] res1 = {{1, 2}, {3, 4}};
        int[][] res2 = {{5, 6}, {7, 8}};
        Test05.addition(res1, res2);
        System.out.println("----------------");
        Test05.subtraction(res1, res2);
    }

}
