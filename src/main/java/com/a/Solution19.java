package com.a;

public class Solution19 {

    private int maxDiameter = 0;//记录全局最大直径（边数）

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        getHigh(root);

        return maxDiameter;
    }

    private int getHigh(TreeNode root) {
        if (root == null) return 0;

        int left = getHigh(root.left);
        int right = getHigh(root.right);

        //当前节点的直径（边数）
        int curDiameter = left + right;
        maxDiameter = Math.max(maxDiameter, curDiameter);

        return Math.max(left, right) + 1;

    }
}
