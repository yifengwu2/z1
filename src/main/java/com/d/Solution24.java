package com.d;

public class Solution24 {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;

        // 如果要拿全部卡片，直接返回总和
        if (n == k) {
            int total = 0;
            for (int cardPoint : cardPoints) {
                total += cardPoint;
            }
            return total;
        }

        int windowSize = n - k;
        int currentSum = 0;

        //先算第一个窗口大小
        for (int i = 0; i < windowSize; i++) {
            currentSum += cardPoints[i];
        }

        int minSum = currentSum;//初始化最小和
        // 滑动窗口：从 windowSize 开始，加入右边新元素，移除左边旧元素（当前窗口最左边的元素）
        for (int right = windowSize; right < n; right++) {
            currentSum += cardPoints[right];// 加入右边
            currentSum -= cardPoints[right-windowSize];// 移除左边
            minSum = Math.min(minSum, currentSum);
        }

        int totalSum = 0;

        for (int cardPoint : cardPoints) {
            totalSum += cardPoint;
        }

        return totalSum - minSum;
    }

}
