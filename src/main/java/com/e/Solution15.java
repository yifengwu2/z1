package com.e;

public class Solution15 {
    public int minLengthAfterRemovals(String s) {
        char a = 'a';
        int counta = 0;
        int countb = 0;
        int n = s.length();
        for (char c : s.toCharArray()) {
            if (c == a) {
                counta++;
            } else {
                countb++;
            }
        }
        if (counta == n || countb == n) {
            return n;
        }
        if (counta == countb) {
            return 0;
        }
        int min = Math.min(counta, countb);

        return n - 2 * min;


    }
}
