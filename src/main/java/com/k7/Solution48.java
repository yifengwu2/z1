package com.k7;

import java.util.Stack;

public class Solution48 {
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        int index = -1;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                index = i;
                break;
            }
        }
        if (index == -1) return true;
        for (int i = index; i < n; i++) {
            if (s.charAt(i) == '1') {
                return false;
            }
        }
        return true;
    }
}
