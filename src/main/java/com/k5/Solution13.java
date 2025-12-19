package com.k5;

import java.util.ArrayList;
import java.util.List;

public class Solution13 {
    public List<String> buildArray(int[] target, int n) {
        String push = "Push";
        String pop = "Pop";
        int len = target.length;
        List<String> list = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 == target[j++]) {
                list.add(push);
            } else {
                list.add(push);
                list.add(pop);
            }
            if (j == len) {
                break;
            }
        }
        return list;
    }
}
