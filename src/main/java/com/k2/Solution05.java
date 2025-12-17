package com.k2;

public class Solution05 {
    public boolean isSubsequence(String s, String t) {
        int n1 = s.length();
        int j = 0;
        if (s.isEmpty()) return true;
        if (t.isEmpty()) return false;

        for (int i = 0; i < t.length(); i++) {
            if (j < n1) {
                char c = s.charAt(j);
                char c2 = t.charAt(i);
                if (c == c2) {
                    j++;
                }
            }
        }
        return j  == n1;
    }

}
