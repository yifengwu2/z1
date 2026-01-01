package com.f;

import java.util.Map;

public class Solution31 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(digits[i]);
        }
        String str = sb.toString();
        Long l = Long.valueOf(str);
        long num = l + 1;
        String s = String.valueOf(num);
        Map<Character, Integer> map = Map.of('0', 0, '1', 1, '2', 2, '3', 3, '4', 4, '5', 5, '6', 6, '7', 7, '8', 8, '9', 9);
        int[] res = new int[s.length()];
        int j = 0;
        for (char c : s.toCharArray()) {
            Integer i1 = map.get(c);
            res[j++] = i1;
        }
        return res;

    }
}
