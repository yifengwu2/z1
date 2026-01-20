package com.k7;

public class Solution18 {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int count) {
        if (root == null) return 0;
        count = count * 10 + root.val;
        if (root.left == null && root.right == null) {
            return count;
        }
        return dfs(root.left, count) + dfs(root.right, count);
    }
}
