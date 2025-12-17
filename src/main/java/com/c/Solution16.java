package com.c;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution16 {
    public int minimumTotal(List<List<Integer>> triangle) {
        //TODO 动态规划dp
        //局部最优不一定是全局最优
        //有时候要考虑一下，看看眼前利益是否是最优解

        int n = triangle.size();
        int[][] dp = new int[n][n];

        //先存最后一层
        List<Integer> list1 = triangle.get(n - 1);
        for (int i1 = 0; i1 < list1.size(); i1++) {
            dp[n - 1][i1] = list1.get(i1);
        }

        //从倒数第二行出发
        for (int i = n - 2; i >= 0; i--) {
            //当前行里面的数
            List<Integer> list = triangle.get(i);

            for (int j = 0; j < list.size(); j++) {
                //当前行的数
                Integer cur = list.get(j);

                dp[i][j] = cur + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);

//                int min = Integer.MAX_VALUE;
//                //最后一层遍历j或j+1就行
//                for (int k = j; k <= j + 1; k++) {
//                    //求最小路径
//                    min = Math.min(min, cur + list1.get(j));
//                }
//                //表示从当前行到底部的最小路径
//                dp[i][j] = min;
            }

        }
        return dp[0][0];
    }
}

