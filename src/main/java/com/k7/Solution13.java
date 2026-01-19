package com.k7;

public class Solution13 {
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return sum;
    }

    private void dfs(TreeNode root, boolean isLeft) {
        if (root == null) return;
        if (root.left == null && root.right == null && isLeft) {
            sum += root.val;
        }
        dfs(root.left, true);
        dfs(root.right, false);
    }
}
