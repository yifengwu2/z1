package com.k7;


public class Solution15 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHigh = minDepth(root.left);
        int rightHigh = minDepth(root.right);
        int high = Math.min(leftHigh, rightHigh) + 1;

        if (root.left == null && root.right != null) {
            return rightHigh + 1;
        }
        if (root.right == null && root.left != null) {
            return leftHigh + 1;
        }
        return high;
    }
}
