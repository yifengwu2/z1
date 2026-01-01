package com.f;

public class Solution32 {
    public int[] plusOne(int[] digits) {
        int[] ans = digits.clone();
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                ans[i] = ans[i] + 1;
                return ans;
            } else {
                ans[i] = 0;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;

    }
}
