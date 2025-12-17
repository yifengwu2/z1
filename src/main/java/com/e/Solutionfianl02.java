package com.e;

import java.util.LinkedList;
import java.util.List;

public class Solutionfianl02 {
    List<Integer> list = new LinkedList<>();

    //前序
    public void preorder(TreeNode root) {
        if (root == null) return;
        list.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    public void finalorder(TreeNode root) {
        if (root == null) return;
        finalorder(root.left);
        finalorder(root.right);
        list.add(root.val);
    }

    //求高度
    public int height(TreeNode root) {
        if (root == null) return 0;
        int lefthigh = height(root.left);
        int righthigh = height(root.right);

        return Math.max(lefthigh, righthigh) + 1;
    }

    //叶结点
    public int countLeaves(TreeNode root) {
        if (root == null) return 0;
        int left = countLeaves(root.left);
        int right = countLeaves(root.right);

        if (root.right == null && root.left == null) {
            return 1;
        }
        return left + right;
    }

}

class TreeNode {
    int val;
    TreeNode left;

    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

}
