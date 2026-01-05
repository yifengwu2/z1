package com.k6;

public class Solution46 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode cur1 = list1;
        int i = 0;
        ListNode prevNode = null;
        ListNode currentNode = null;
        while (cur1 != null) {
            if (i == a - 1) {
                prevNode = cur1;
            }
            cur1 = cur1.next;
            i++;
            if (i == b + 1) {
                currentNode = cur1;
                break;
            }
        }
        ListNode tail = null;
        ListNode cur2 = list2;
        while (cur2 != null) {
            cur2 = cur2.next;
            if (cur2 != null && cur2.next == null) {
                tail = cur2;
            }
        }
        if (tail != null && prevNode != null) {
            tail.next = currentNode;
            prevNode.next = list2;
        }
        return list1;

    }
}
