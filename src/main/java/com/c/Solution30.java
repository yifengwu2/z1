package com.c;

import java.util.ArrayList;
import java.util.List;

public class Solution30 {
    public String[] permutation(String S) {
        List<String> results = new ArrayList<>();
        int n = S.length();
        StringBuilder path = new StringBuilder();//当前路径
        boolean[] used = new boolean[n + 1];//标记每个字符是否被用过


        backtrack(S, used, path, results);

        return results.toArray(new String[0]);

    }

    private void backtrack(String s, boolean[] used, StringBuilder path, List<String> results) {
        if (path.length() == s.length()) {
            results.add(path.toString()); // 收集结果
            return;
        }
        //遍历每一个字符
        for (int i = 0; i < s.length(); i++) {
            if (used[i]) {
                continue;
            }
            char c = s.charAt(i);
            path.append(c);
            used[i] = true;

            //进入下一层决策
            backtrack(s,used,path,results);

            path.deleteCharAt(path.length()-1);
            used[i]=false;
        }

    }
}
