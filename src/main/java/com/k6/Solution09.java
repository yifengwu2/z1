package com.k6;

public class Solution09 {
    public int maxScore(String s) {
        int total = s.charAt(0) == '0' ? 1 : 0;
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1') {
                total++;
            }
        }

        int maxCount = total;
        int left = 0;
        int right = total;
        for (int i = 1; i < n; i++) {
            char prev = s.charAt(i);
            if (prev == '0') {
                left++;
            } else if (prev == '1') {
                right--;
            }
            int cost = left + right;
            if (cost > maxCount) {
                maxCount = cost;
            }

        }
        return maxCount;
    }
}
