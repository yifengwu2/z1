package com.k7;

import java.util.List;

public class Solution35 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur=head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        return prev;
    }
}
