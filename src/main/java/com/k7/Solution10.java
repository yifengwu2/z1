package com.k7;

import java.util.ArrayList;
import java.util.List;

public class Solution10 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list = new ArrayList<>();
        save(root1, list);
        List<Integer> list2 = new ArrayList<>();
        save(root2, list2);
//        if (list.size() != list2.size()) return false;
//        for (int i = 0; i < list.size(); i++) {
//            if (!Objects.equals(list.get(i), list2.get(i))) {
//                return false;
//            }
//        }
        return list.equals(list2);
    }

    private List<Integer> save(TreeNode root, List<Integer> list) {
        if (root == null) return list;
        if (root.left == null && root.right == null) list.add(root.val);
        save(root.left, list);
        save(root.right, list);
        return list;
    }
}
