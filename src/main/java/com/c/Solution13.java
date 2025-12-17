package com.c;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution13 {
    public ListNode1 modifiedList(int[] nums, ListNode1 head) {
        if (head == null) return null;
        //定义个虚拟头结点
        ListNode1 dummy = new ListNode1();
        ListNode1 node1 = dummy;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode1 cur = head;
        while (cur != null) {
            if (!set.contains(cur.val)) {
                node1.next = cur;
                node1 = node1.next;
            }
            cur = cur.next;

        }
        node1.next = null;

        return dummy.next;
    }
}

class ListNode1 {
    int val;
    ListNode1 next;

    ListNode1() {
    }

    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }
}

