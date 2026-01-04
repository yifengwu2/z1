package com.k6;

import java.util.*;

public class Solution35 {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String top = words[i];
            for (int j = 0; j < words.length; j++) {
                if (j == i) continue;
                String left = words[j];
                for (int k = 0; k < words.length; k++) {
                    if (j == k || k == i) continue;
                    String right = words[k];
                    for (int l = 0; l < words.length; l++) {
                        if (l == k || l == j || l == i) continue;
                        String bottom = words[l];
                        if (top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0) && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                            List<String> list = new ArrayList<>();
                            list.add(top);
                            list.add(left);
                            list.add(right);
                            list.add(bottom);
                            res.add(list);
                        }
                    }
                }
            }
        }
        res.sort((a, b) -> {
            for (int i = 0; i < 4; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });
        return res;
    }
}

