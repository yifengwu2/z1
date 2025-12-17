package com.d;

public class Solution23 {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = 0;
        for (int num : cardPoints) {
            sum += num;
        }
        int left = 0;

        int minSum = 0;
        //找窗口n-k大小最小的,在用总和-最小得到最大
        for (int right = 0; right < n; right++) {

            //达到窗口长度
            if (right - left + 1 == n - k) {
                //计算当前窗口大小
                int currentSum = 0;
                for (int i = left; i <= right; i++) {
                    currentSum += cardPoints[i];
                }
                minSum = Math.min(minSum, currentSum);

            }
            //超出窗口
            if (right - left + 1 > n - k) {
                left++;
            }
        }

        return sum - minSum;
    }
}
