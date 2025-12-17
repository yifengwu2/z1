package com.c;

import java.util.ArrayList;
import java.util.List;

public class Solution27 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] res = new int[m * n];
        int count = 0;

        for (int level = 0; level <= m + n - 2; level++) {
            //放一个临时列表
            List<Integer> temp = new ArrayList<>();
            // 收集这一层的所有元素（保持自然顺序：i 从小到大）
            for (int i = 0; i < m; i++) {
                int j = level - i;
                // 判断列是否越界
                if (j >= 0 && j < n) {
                    temp.add(mat[i][j]);
                }
            }
            // 根据 level 奇偶性决定添加顺序
            if (level % 2 == 0) {
                //如果是偶数，逆序添加
                for (int i1 = temp.size() - 1; i1 >= 0; i1--) {
                    res[count++] = temp.get(i1);
                }
            } else {
                // 奇数层：从上往下 → 正序添加
                for (Integer integer : temp) {
                    res[count++] = integer;
                }
            }
        }
        return res;
    }
}
