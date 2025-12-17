package com.k4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution14 {
    public int numEquivDominoPairs(int[][] dominoes) {
        for (int[] ans : dominoes) {
            if (ans[0] > ans[1]) {
                swap(ans, 0, 1);
            }
        }
        //值，频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < dominoes.length; i++) {
            int[] dominoe = dominoes[i];
            int i1 = dominoe[0] * 10 + dominoe[1];
            if (map.containsKey(i1)) {
                map.put(i1, map.get(i1) + 1);
            } else {
                map.put(i1, map.getOrDefault(i1, 0) + 1);
            }

        }
        int pair = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer cnt = entry.getValue();
            pair += cnt * (cnt - 1) / 2;
        }
        return pair;
    }

    private void swap(int[] ans, int i, int j) {
        int temp = ans[i];
        ans[i] = ans[j];
        ans[j] = temp;
    }
}
