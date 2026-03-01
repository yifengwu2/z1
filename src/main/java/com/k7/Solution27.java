package com.k7;

public class Solution27 {
    public int minPartitions(String n) {
        char[] chars = n.toCharArray();
        int max = 0;
        for (char c : chars) {
            int i = c - '0';
            max = Math.max(max, i);
        }
        return max;
    }
}
