package com.k7;

public class Solution02 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int j = 1;
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        k = k % len;
        if (k == 0) return head;
        int i = len - k;

        ListNode targetnode = head;
        while (j < i) {
            targetnode = targetnode.next;
            j++;
        }
        ListNode curr = targetnode;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;
        head = targetnode.next;

        targetnode.next = null;

        return head;

    }
}
