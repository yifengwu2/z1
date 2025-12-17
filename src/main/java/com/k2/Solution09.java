package com.k2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Solution09 {
    public String findLongestWord(String s, List<String> dictionary) {
        int n = dictionary.size();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s1 = dictionary.get(i);
            if (isTrue(s, s1)) {
                list.add(s1);
            }
        }
        if (list.isEmpty()) {
            return "";
        }
        list.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            } else {
                return a.compareTo(b);
            }
        });

        return list.get(0);
    }

    // 判断 word 是否是 s 的子序列
    private boolean isTrue(String s, String target) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (j < target.length()) {
                if (s.charAt(i) == target.charAt(j)) {
                    j++;
                }
            }
        }
        return j - 1 == target.length();
    }
}
