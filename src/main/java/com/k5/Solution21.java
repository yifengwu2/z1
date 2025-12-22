package com.k5;

public class Solution21 {
    public int mirrorDistance(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder(s);
        StringBuilder reverse = sb.reverse();
        String str = reverse.toString();
        int i = Integer.parseInt(str);
        return Math.abs(n - i);
    }
}
