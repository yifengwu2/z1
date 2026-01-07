package com.k6;

import java.util.Map;

public class Solution53 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = reserve(l1);
        ListNode r2 = reserve(l2);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (r1 != null || r2 != null || carry != 0) {
            int sum = carry;
            if (r1 != null) {
                sum += r1.val;
                r1 = r1.next;
            }
            if (r2 != null) {
                sum += r2.val;
                r2 = r2.next;
            }
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        return reserve(dummy.next);
    }

    private ListNode reserve(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
