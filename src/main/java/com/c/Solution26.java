package com.c;

import java.util.*;

public class Solution26 {
    public String sortVowels(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        char[] res = new char[n];
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        //存下标
        List<Integer> list = new ArrayList<>();
        /// map会覆盖，改用list
        List<Character> characterList = new ArrayList<>();

        //字符->下标
//        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (!set.contains(c)) {
                res[i] = c;
            } else {
                characterList.add(c);
                list.add(i);

            }
        }

        //排序
        List<Character> characters = new ArrayList<>(characterList);
        characters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return a - b;
            }
        });

        for (int i = 0; i < characters.size(); i++) {
            Integer k = list.get(i);
            res[k] = characters.get(i);

        }

        return String.valueOf(res);

    }
}
