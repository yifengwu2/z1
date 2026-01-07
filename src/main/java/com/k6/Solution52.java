package com.k6;

import java.util.ArrayList;
import java.util.List;

public class Solution52 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
