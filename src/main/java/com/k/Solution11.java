package com.k;

public class Solution11 {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        final int MOD = 1000000007;

        //dp[i][j][k]表示到达（i,j）且路径%k==r的路径数
        long[][][] dp = new long[m][n][k];

        //初始化起点
        int startVal = grid[0][0];
        dp[0][0][startVal % k] = 1;

        //填表
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue; // 已初始化

                int val = grid[i][j];
                for (int r = 0; r < k; r++) {
                    long ways = 0;

                    //来自上方
                    if (i > 0) {
                        ways = (ways + dp[i - 1][j][r]) % MOD;
                    }

                    //来自上方
                    if (j > 0) {
                        ways = (ways + dp[i][j - 1][r]) % MOD;
                    }

                    // 当前状态的余数是 (r + val) % k
                    int newR = (r + val) % k;
                    dp[i][j][newR] = (dp[i][j][newR] + ways) % MOD;
                }
            }
        }
        return (int) dp[m - 1][n - 1][0];
    }
}
