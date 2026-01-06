package com.k6;

import java.util.ArrayList;
import java.util.List;

public class Solution47 {
    public ListNode removeNodes(ListNode head) {
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] res = new int[list.size()];
        int j = 0;
        for (Integer i : list) {
            res[j++] = i;
        }
        int max = Integer.MIN_VALUE;
        for (int i = res.length - 1; i >= 0; i--) {
            max = Math.max(max, res[i]);
            if (res[i] < max) {
                res[i] = 0;
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur1 = dummy;
        int k = 0;
        while (k < res.length) {
            if (res[k] != 0) {
                cur1.next = new ListNode(res[k]);
                cur1 = cur1.next;
            }
            k++;
        }
        return dummy.next;

    }
}
