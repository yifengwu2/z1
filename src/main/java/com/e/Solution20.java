package com.e;

public class Solution20 {
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        int left = 0;
        int count1 = 0;
        int count0 = 0;
        int num = 0;
        char[] chars = s.toCharArray();
        for (int right = 0; right < n; right++) {
            char c = chars[right];
            if (c == '0') {
                count0++;
            } else {
                count1++;
            }
            while (count0 > k && count1 > k) {
                char c1 = chars[left];
                if (c1 == '0') {
                    count0--;
                } else {
                    count1--;
                }
                left++;
            }
                num += right - left + 1;

        }
        return num;
    }
}
