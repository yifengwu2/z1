package com.f;

public class Solution20 {
    public int totalWaviness(int num1, int num2) {
        int count = 0;
        for (int i = num1; i <= num2; i++) {
            String s = String.valueOf(i);
            int n = s.length();
            if (n <= 2) continue;

            char[] chars = s.toCharArray();
            for (int j = 1; j < n - 1; j++) {
                int mid = chars[j] - '0';
                int left = chars[j - 1] - '0';
                int right = chars[j + 1] - '0';
                if (mid > left && mid > right) {
                    count++;
                } else if (mid < left && mid < right) {
                    count++;
                }

            }

        }
        return count;
    }
}
