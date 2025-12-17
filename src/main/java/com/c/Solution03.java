package com.c;

import java.util.Arrays;
import java.util.Collections;

public class Solution03 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return rebuild(nums, 0, nums.length-1);

    }

    /**
     * 在 nums[left...right] 范围内构建 BST
     */
    private TreeNode rebuild(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //选中间值作为根节点
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = rebuild(nums, left, mid-1);

        root.right = rebuild(nums, mid+1, right);

        return root;

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
