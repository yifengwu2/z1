package com.c;

import java.util.*;

public class Solution12 {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, int[]> map = new HashMap<>();
        int i = 1;
        for (int[] ans : languages) {
            map.put(i, ans);
            i++;
        }
        //找出无法沟通的两个人，把它放入set
        Set<Integer> needTeach = new HashSet<>();

        for (int[] friendship : friendships) {
            int user1 = friendship[0];
            int user2 = friendship[1];

            //获取用户会的语言
            int[] u = map.get(user1);
            int[] v = map.get(user2);

            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int j : u) {
                set1.add(j);
            }
            for (int k : v) {
                set2.add(k);
            }

            //语言不通放set里
            if (!haveCommand(set1, set2)) {
                needTeach.add(user1);
                needTeach.add(user2);
            }

        }
        //枚举语言数
        //最坏的结果
        int minTeach = needTeach.size();

        int teachCount;

        for (int lang = 1; lang < n; lang++) {
            teachCount = 0;

            for (int user : needTeach) {//遍历每个要教的人
                int[] userAns = map.get(user);//获取他们会的语言
                boolean knows = false;
                for (int l : userAns) {
                    //他们会这门语言吗
                    if (l == lang) {
                        knows = true;
                        break;
                    }
                }
                //不会就教他们
                if (!knows) {
                    teachCount++;
                }

            }
            minTeach = Math.min(minTeach, teachCount);

        }
        return minTeach;
    }

    //判断两个语言是否有交集
    private boolean haveCommand(Set<Integer> set1, Set<Integer> set2) {
        if (set1.isEmpty() || set2.isEmpty()) return false;

        //遍历小集合
        if (set1.size() > set2.size()) {
            //交换位置
            Set<Integer> temp = set1;
            set1 = set2;
            set2 = temp;
        }

        for (int i : set1) {
            if (set2.contains(i)) {
                return true;
            }
        }
        return false;
    }
}
