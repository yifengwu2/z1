package com.e;

public class Solution12 {
    public int numSub(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        long total = 0;
        long currentOnes = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                currentOnes++;
            } else {
                total += currentOnes * (currentOnes + 1) / 2;
                currentOnes = 0;
            }
        }
        // 结算最后一段
        total += currentOnes * (currentOnes + 1) / 2;

        return (int) (total % 1000000007);
    }

}
