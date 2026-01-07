package com.k7;


public class Solution01 {
    public ListNode doubleIt(ListNode head) {
        ListNode reserve = reserve(head);
        ListNode cur = reserve;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur1 = dummy;
        while (cur != null || carry != 0) {
            int sum = carry;
            if (cur != null) {
                sum += cur.val * 2;
                cur = cur.next;
            }
            cur1.next = new ListNode(sum % 10);
            carry = sum / 10;
            cur1 = cur1.next;
        }
        return reserve(dummy.next);
    }

    private ListNode reserve(ListNode node) {
        ListNode cur = node;
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
