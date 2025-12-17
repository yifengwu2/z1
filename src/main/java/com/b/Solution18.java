package com.b;

import java.util.*;

public class Solution18 {
    public String compressString(String S) {
        int n = S.length();
        if (n == 0) return S;
        int i = 0;

        StringBuilder sb = new StringBuilder();

        char[] chars = S.toCharArray();
        while (i < n) {
            char c1 = chars[i];
            int j = i + 1;
            while (j < n && S.charAt(j) == c1) {
                j++;
            }
            sb.append(c1);
            sb.append(j - i);

            i = j;
        }

        if (sb.toString().length() >= n) {
            return S;
        }

        return sb.toString();
    }
}
