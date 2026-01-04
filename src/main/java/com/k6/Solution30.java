package com.k6;

import java.util.ArrayList;
import java.util.List;

public class Solution30 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            if (cur.val != val) {
                list.add(cur.val);
            }
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur1 =dummy;
        for (Integer i : list) {
            cur1.next = new ListNode(i);
            cur1 = cur1.next;
        }
        return dummy.next;

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
