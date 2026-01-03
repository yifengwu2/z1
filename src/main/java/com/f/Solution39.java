package com.f;

import java.util.ArrayList;
import java.util.List;

public class Solution39 {
    public ListNode mergeNodes(ListNode head) {
        ListNode cur = head.next;
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        while (cur != null) {
            int val = cur.val;
            if (val == 0) {
                list.add(sum);
                sum = 0;
            }
            sum += val;
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur1=dummy;
        for (Integer l : list) {
            cur1.next = new ListNode(l);
            cur1 = cur1.next;
        }
        return dummy.next;
    }

}
