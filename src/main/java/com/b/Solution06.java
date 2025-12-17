package com.b;

public class Solution06 {
    public boolean isFlipedString(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 != n2) return false;
        if (n1 == 0) return true;
        String s = s1 + s1;

        for (int i = 0; i < n1; i++) {
            char c = s.charAt(i);
            String window = s.substring(i, i + n1);

            if (window.equals(s2)) {
                return true;
            }
        }
        return false;
    }

}
