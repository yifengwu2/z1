package com.a;

public class Solution18 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // 两个节点都为空
        if (left == null && right == null) return true;
        // 有一个为空，另一个不为空
        if (left == null || right == null) return false;
        // 值不相等
        if (left.val != right.val) return false;

        // 递归比较：左的左 vs 右的右，左的右 vs 右的左
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

}
