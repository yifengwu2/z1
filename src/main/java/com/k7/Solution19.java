package com.k7;

public class Solution19 {
    public boolean isUnivalTree(TreeNode root) {
       return isUnivalTree(root, root.val);
    }

    public boolean isUnivalTree(TreeNode root, int val) {
        if (root == null) return true;
        if (root.val != val) {
            return false;
        }
        return isUnivalTree(root.left, root.val) && isUnivalTree(root.right, root.val);
    }
}
