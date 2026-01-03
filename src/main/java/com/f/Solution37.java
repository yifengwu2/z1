package com.f;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution37 {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (ListNode list : lists) {
            ListNode cur = list;
            while (cur != null) {
                pq.offer(cur.val);
                cur = cur.next;
            }
        }
        ListNode root = new ListNode(0);
        ListNode cur = root;
        while (!pq.isEmpty()) {
            Integer val = pq.poll();
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return root.next;
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
