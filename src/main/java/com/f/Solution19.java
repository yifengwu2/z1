package com.f;

import java.util.Arrays;

public class Solution19 {
    public int minimumFlips(int n) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            n /= 2;
        }
        String s = sb.reverse().toString();
        String rev = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < rev.length() ; i++) {
            if (s.charAt(i) != rev.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
