package com.b;

public class Solution16 {
    public int totalMoney(int n) {
        if (n < 0) return 0;

        //计算第n天存了多少钱
        int week = (n - 1) / 7;//第几周（从0开始）
        int DayOfWeek = (n - 1) % 7;//本周第几天（0-6）
        int todayMoney = week + DayOfWeek + 1;

        return todayMoney + totalMoney(n - 1);
    }
}
