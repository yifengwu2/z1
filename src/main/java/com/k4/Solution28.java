package com.k4;

public class Solution28 {
    public long minMoves(int[] balance) {
        int n = balance.length;
        int sum = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (balance[i] < 0) {
                index = i;
            }
            sum += balance[i];
        }
        if (sum < 0) return -1;

        int i = index - 1;
        int j = index + 1;

        //负数在右边
        int k = index;
        if (index == n - 1) {
            int need = -balance[index];//缺的钱
            int total = 0;
            int cnt = 0;//步数
            while (k > 0 && total < balance[k]) {
                k--;
                int b = balance[k];
                int take = Math.min(b, need - total);
                int dist = index - k;
                cnt += take * dist;
                total += take;
            }
            return cnt;
        }
        // 负数在最左边
        if (index == 0) {
            int need = -balance[index];
            int totalGot = 0;
            int cnt = 0;

            // 从 index+1 往右扫到 n-1（用你的 j）
            int l = index;
            while (l < n - 1 && totalGot < need) {
                l++;
                int b = balance[l];
                if (b <= 0) continue;
                int take = Math.min(b, need - totalGot);
                int dist = l - index;
                cnt += take * dist;
                totalGot += take;
            }
            return cnt;
        }


        //负数在中间
        if (i > 0 && j < n) {
            if (balance[i] + balance[j] < balance[index]) {
                i--;
                j++;


            } else {
                return balance[index];
            }
        }


        return 0;
    }
}
