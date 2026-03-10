package com.k7;

import java.util.ArrayList;
import java.util.List;

public class Solution52 {
    public boolean isPalindrome(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        ListNode prev = null;
        ListNode cur2 = head;
        while (cur2 != null) {
            ListNode temp = cur2.next;
            cur2.next=prev;
            prev=cur2;
            cur2 = temp;
        }
        ListNode node = prev;
        while (node != null) {
            list2.add(node.val);
            node = node.next;
        }
        return list2.equals(list);

    }
}
