package com.k6;

import java.util.*;

public class Solution51 {
    public int maxLevelSum(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        int level = 0;
        int maxLevel = 0;
        int maxSum = Integer.MIN_VALUE;
        deque.add(root);
        while (!deque.isEmpty()) {
            level++;
            int sum = 0;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                sum += poll.val;
                if (poll.left != null) deque.offer(poll.left);
                if (poll.right != null) deque.offer(poll.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
        }
        return maxLevel;

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
