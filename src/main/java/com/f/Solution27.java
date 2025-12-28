package com.f;

public class Solution27 {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long min = Math.min(need1, need2);
        long max = Math.max(need1, need2);
        long Cost1 = Long.MAX_VALUE;

        long Cost2 = max * costBoth;
        long n1 = need1;
        long n2 = need2;
        for (int i = 0; i <= min; i++) {
            long i1 = (long) costBoth * i + (long) n1 * cost1 + (long) n2 * cost2;
            Cost1 = Math.min(Cost1, i1);
            n1--;
            n2--;
        }
        return Math.min(Cost1, Cost2);

    }
}
