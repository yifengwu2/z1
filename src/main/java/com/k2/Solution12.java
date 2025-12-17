package com.k2;

import java.util.ArrayList;
import java.util.List;

public class Solution12 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        for (String query : queries) {
            list.add(isMatch(query, pattern));
        }
        return list;
    }

    private boolean isMatch(String query, String pattern) {
        int j = 0;//pattern的指针
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (j < pattern.length() && c == pattern.charAt(j)) {
                j++;
            } else if (c >= 'a' && c <= 'z') {
            } else {
                return false;
            }

        }
        return j == pattern.length();

    }
}
