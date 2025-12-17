package com.b;

import java.util.HashSet;
import java.util.Set;

public class Solution19 {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        while ( cur.next != null) {
            if (!set.contains(cur.next.val)) {
                set.add(cur.next.val);
                cur = cur.next;

            } else {
                cur.next = cur.next.next;
            }
        }
        return head;
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
