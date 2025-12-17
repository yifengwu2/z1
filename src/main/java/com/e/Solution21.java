package com.e;

import java.util.HashMap;
import java.util.Map;

public class Solution21 {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int counta = 0;
        int countb = 0;
        int countc = 0;
        int left = 0;
        int number = 0;

        for (int right = 0; right < n; right++) {
            char c = chars[right];
            if (c == 'a') counta++;
            if (c == 'b') countb++;
            if (c == 'c') countc++;
            while (counta >= 1 && countb >= 1 && countc >= 1) {
                char c1 = chars[left];
                if (c1 == 'a') {
                    counta--;
                } else if (c1 == 'b') {
                    countb--;
                } else {
                    countc--;
                }
                left++;
            }
            number += left;
        }
        return number;
    }
}
