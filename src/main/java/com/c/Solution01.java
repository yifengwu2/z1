package com.c;

import com.sun.source.tree.Tree;

import javax.swing.tree.TreeNode;

public class Solution01 {
    public int kthToLast(ListNode head, int k) {
        int length = 0;
        ListNode cur = head;

        while (cur != null) {
            length++;
            cur = cur.next;
        }
        int i = length - k;

        ListNode node = head;
        for (int i1 = 0; i1 < i; i1++) {
            node = node.next;
        }

        return node.val;

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
