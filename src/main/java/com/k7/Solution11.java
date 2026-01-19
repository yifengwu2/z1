package com.k7;

import java.util.HashSet;
import java.util.Set;

public class Solution11 {
    private final Set<Integer> set = new HashSet<>();

    public int numColor(TreeNode root) {
        pre(root);
        return set.size();

    }

    private void pre(TreeNode root) {
        if (root == null) return;
        set.add(root.val);
        pre(root.left);
        pre(root.right);
    }

}
