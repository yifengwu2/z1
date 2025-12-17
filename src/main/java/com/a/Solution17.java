package com.a;

public class Solution17 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        TreeNode leftNode = invertTree(root.left);

        TreeNode rightNode = invertTree(root.right);

        root.left = rightNode;

        root.right = leftNode;

        return root;
    }
}
