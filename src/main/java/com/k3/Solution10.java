package com.k3;

public class Solution10 {
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        if (n <= 2) return false;

        int i = 0;
        int counta = 0;
        int countb = 0;
        while (i < n) {

            int j = i;
            while (j + 1 < n && colors.charAt(j) == colors.charAt(j + 1)) {
                j++;
            }
            if (j - i + 1 >= 3 && colors.charAt(i) == 'A') {
                counta = j - i - 2;
            }
            if (j - i + 1 >= 3 && colors.charAt(i) == 'B') {
                countb = j - i - 2;
            }
            i = j + 1;
        }
        if (counta == countb) return false;
        return counta > countb;
    }
}
