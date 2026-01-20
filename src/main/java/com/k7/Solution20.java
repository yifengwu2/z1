package com.k7;

public class Solution20 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null) return false;
        if (q == null) return false;

        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }

}
