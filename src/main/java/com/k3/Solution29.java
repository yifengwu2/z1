package com.k3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution29 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int[] ans = new int[n];

        for (int i1 = 0; i1 < words.length; i1++) {
            String word = words[i1];
            char[] chars = word.toCharArray();
            if (set.contains(chars[0]) && set.contains(chars[word.length() - 1])) {
                ans[i1] = 1;
            }

        }
        int[] s = new int[n + 1];

        for (int k = 0; k < n; k++) {
            s[k + 1] = s[k] + ans[k];
        }

        int[] res = new int[queries.length];
        int j = 0;
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            res[j++] = s[right + 1] - s[left];
        }
        return res;
    }
}
