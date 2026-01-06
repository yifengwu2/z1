package com.k6;

import java.util.ArrayList;
import java.util.List;

public class Solution48 {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head.next == null) return head;
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur.next != null) {
            int gcd = gcd(cur.val, cur.next.val);
            list.add(cur.val);
            list.add(gcd);
            cur = cur.next;
        }
        list.add(cur.val);
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        ListNode dummy = new ListNode(0);
        ListNode cur1 = dummy;
        int j = 0;
        while (j < array.length) {
            cur1.next = new ListNode(array[j++]);
            cur1 = cur1.next;
        }
        return dummy.next;

    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
