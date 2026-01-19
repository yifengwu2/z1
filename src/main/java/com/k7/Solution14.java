package com.k7;

public class Solution14 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int lefthigh = maxDepth(root.left);
        int righthigh = maxDepth(root.right);


        return Math.min(lefthigh, righthigh) + 1;

    }
}
