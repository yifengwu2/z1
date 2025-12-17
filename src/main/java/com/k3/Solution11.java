package com.k3;

import java.util.ArrayList;
import java.util.List;

public class Solution11 {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = groups.length;
        boolean flag = groups[0] != 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int group = groups[i];
            if (group == 0) {
                if (!flag) {
                    list.add(words[i]);
                    flag = true;
                }
            } else if (group == 1) {
                if (flag) {
                    list.add(words[i]);
                    flag = false;
                }
            }
        }
        return list;

    }
}
