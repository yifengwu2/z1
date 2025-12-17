package com.k3;

//超时
public class Solution25 {
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int hour1 = hours[i];
            for (int j = i + 1; j < n; j++) {
                int hour2 = hours[j];
                int time = hour1 + hour2;
                if (time % 24 == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
