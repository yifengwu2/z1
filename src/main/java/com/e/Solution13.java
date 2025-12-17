package com.e;

public class Solution13 {
    public int numSub(String s) {
        int n = s.length();
        int i = 0;
        long total = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                int j = i + 1;
                while (j < n && s.charAt(j) == '1') {
                    j++;
                }
                int l = j - i;
                total += (long) l * (l + 1) / 2;

                i = j;

            } else {
                i++;
            }
        }
        return (int)(total % 1000000007);
    }
}
