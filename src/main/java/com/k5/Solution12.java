package com.k5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution12 {
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(array[i].charAt(0))) {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int head = 0;
        int tail = 0;
        map.put(0, 0);
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            if (map.containsKey(prefix)) {
                int a = map.get(prefix);
                if (i - a > tail - head) {
                    tail = i + 1;
                    head = a;
                }
            } else {
                map.put(prefix, i + 1);
            }
        }
        return Arrays.copyOfRange(array, head, tail);
    }
}
