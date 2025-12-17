package com.k3;

public class Solution17 {
    public int countOdds(int low, int high) {
        int count = 0;
        if (low % 2 == 0) {
            low++;
        }
        for (int i = low; i < high + 1; i += 2) {
            count++;
        }
        return count;
    }
}
