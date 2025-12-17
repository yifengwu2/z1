package com.k;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution06 {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) return -1;

        int rem = 0;
        Set<Integer> seen = new HashSet<>();

        for (int len = 1; ; len++) {
            rem = (10 * rem + 1) % k;
            if (rem == 0) {
                return len;
            }

            if (seen.contains(rem)) {
                return -1;
            }
            seen.add(rem);
        }
    }

}
