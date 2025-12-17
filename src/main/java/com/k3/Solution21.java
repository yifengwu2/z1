package com.k3;

public class Solution21 {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (i * i + j * j == k * k) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
