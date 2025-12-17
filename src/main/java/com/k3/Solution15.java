package com.k3;

import java.util.*;

public class Solution15 {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        long count = 0;
        int n = technique1.length;
        for (int num : technique1) {
            count += num;
        }
        if (k == technique1.length) return count;

        int r = n - k;

        //值，索引
        List<int[]> list = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            if (technique1[l] < technique2[l]) {
                list.add(new int[]{technique2[l] - technique1[l], l});
            }
        }
        list.sort((a, b) -> b[0] - a[0]);
        int i = 0;
        for (int[] ans : list) {
            int num = ans[0];
            count += num;
            i++;
            if (i == r) {
                break;
            }
        }
        return count;
    }
}
