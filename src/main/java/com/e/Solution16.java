package com.e;

import java.util.HashSet;
import java.util.Set;

public class Solution16 {
    public long countDistinct(long n) {
        if (n < 10) return n;

        long count = 0;
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i).replace("0", "");
            set.add(s);
        }
        return set.size();
    }
}
