package com.k7;

public class Solution42 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode prev = dummy;

            while (true) {
                ListNode tail = prev;
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) return dummy.next;
                }

                ListNode next = tail.next;
                ListNode[] re = reverse(prev.next, tail);
                prev.next = re[0];
                re[1].next = next;
                prev = re[1];
            }
        }

        private ListNode[] reverse(ListNode start, ListNode to) {
            ListNode pre = null, cur = start, tail = to.next;
            while (cur != tail) {
                ListNode nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
            }
            return new ListNode[]{to, start};
        }
}
