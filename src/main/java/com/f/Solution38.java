package com.f;

public class Solution38 {
    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            int val = cur.val;
            sb.append(val);
            cur = cur.next;
        }
        String s = sb.toString();
        return Integer.parseInt(s, 2);
    }
}
