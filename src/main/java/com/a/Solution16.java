package com.a;

public class Solution16 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftCount = maxDepth(root.left);

        int rightCount = maxDepth(root.right);

        return Math.max(leftCount, rightCount) + 1;
    }
}
