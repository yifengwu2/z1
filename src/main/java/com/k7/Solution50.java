package com.k7;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class Solution50 {
    public int minimumIndex(int[] capacity, int itemSize) {
        int n = capacity.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cap = capacity[i];
            if (cap >= itemSize) {
                min = Math.min(min, cap);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if (capacity[i] == min) {
                return i;
            }
        }
        return -1;

    }
}
