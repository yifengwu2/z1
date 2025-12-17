package com.k3;

import java.util.ArrayList;
import java.util.List;

public class Solution13 {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();


        for (int i = 0; i < n; i++) {
            int k = 0;
            int j = i;
            while (j < n && k < m && haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if (k == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
