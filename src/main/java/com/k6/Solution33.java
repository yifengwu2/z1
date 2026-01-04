package com.k6;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution33 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        Map<Integer, Integer> map = new TreeMap<>();
        ListNode cur = head;
        while (cur != null) {
            map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur1 = dummy;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                cur1.next = new ListNode(entry.getKey());
                cur1 = cur1.next;
            }
        }
        return dummy.next;
    }
}
