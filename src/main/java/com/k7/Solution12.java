package com.k7;

public class Solution12 {
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        cal(root,root);
        return sum;
    }

    private TreeNode cal(TreeNode root, TreeNode pre) {
        if (root == null) return null;

        cal(root.left, root);
        cal(root.right, root);

        if (root.left == null && root.right == null) {
            if (root == pre.left) {
                sum += root.val;
            }
        }
        return root;
    }


}
