package com.k3;

import java.util.HashMap;
import java.util.Map;

public class Solution24 {
    public int countBeautifulPairs(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int a = GetFirstDigit(nums[i]);
            for (int j = i + 1; j < n; j++) {
                int b = GetLastDigit(nums[j]);
                if (gcd(a, b) == 1) {
                    cnt++;
                }
            }
        }
        return cnt * (cnt - 1) / 2;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int GetFirstDigit(int num) {
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }

    private int GetLastDigit(int num) {
        return num % 10;
    }
}
