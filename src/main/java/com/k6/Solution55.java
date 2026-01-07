package com.k6;

public class Solution55 {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow;

        ListNode prev = null;
        ListNode cur = next;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        ListNode cur1 = prev;
        ListNode curr = head;
        while (cur1 != null && curr != null) {
            if (cur1.val != curr.val) {
                return false;
            }
            cur1 = cur1.next;
            curr = curr.next;
        }
        return true;

    }
}
