package com.k6;

public class Solution50 {
    public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }

    public ListNode reverseList(ListNode cur, ListNode prev) {
        if (cur == null) return prev;
        ListNode temp = cur.next;
        cur.next=prev;
        return reverseList(temp, cur);
    }

}
