package com.k7;

import java.util.ArrayList;
import java.util.List;

public class Solution54 {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        dfs(0, 0, "", n, list);
        return list;
    }

    private void dfs(int l, int r, String s, int n, List<String> list) {
        if (s.length() == 2 * n) {
            list.add(s);
            return;
        }
        if (l < n) dfs(l + 1, r, s + "(", n, list);
        if (r < l) dfs(l, r + 1, s + ")", n, list);
    }
}
