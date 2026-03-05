package com.k7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution45 {
    public String reverseByType(String s) {
        int n = s.length();
        if (n == 1) return s;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) (97 + i));
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (list.contains(s.charAt(i))) {
                sb.append(s.charAt(i));
            } else {
                sb2.append(s.charAt(i));
            }
        }
        String str = sb.reverse().toString();
        char[] chars1 = sb2.reverse().toString().toCharArray();
        char[] chr = str.toCharArray();
        char[] chars = s.toCharArray();
        int j = 0;
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            if (list.contains(chars[i])) {
                chars[i] = chr[j++];
            } else {
                chars[i] = chars1[k++];
            }
        }
        return String.valueOf(chars);
    }
}
