package com.k7;

public class Solution44 {
    public int countMonobit(int n) {
        int cnt = 0;
        for (int i = 0; i <= n; i++) {
            int num = Integer.bitCount(i);
            String s = num + "";
            if (s.length() == 1) {
                cnt++;
            } else {
                if (!s.contains("0")) {
                    cnt++;
                }
                if (!s.contains("1")){
                    cnt++;
                }

            }
        }
        return cnt;
    }
}
