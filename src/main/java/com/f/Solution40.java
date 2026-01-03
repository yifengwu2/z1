package com.f;

import java.util.ArrayList;
import java.util.List;

public class Solution40 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode cur = head.next;
        ListNode prev = head;
        List<Integer> list = new ArrayList<>();
        int j = 1;
        while (cur.next != null) {
            int val = cur.next.val;
            int preval = prev.val;
            if (cur.val < val && cur.val < preval) {
                list.add(j);
            }
            if (cur.val > val && cur.val > preval) {
                list.add(j);
            }
            prev = cur;
            cur = cur.next;
            j++;
        }
        if (list.size() < 2) {
            return new int[]{-1, -1};
        }

        int n = list.size();
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDist = Math.min(minDist, list.get(i) - list.get(i - 1));
        }
        int maxDist = list.get(n - 1) - list.getFirst();
        return new int[]{minDist, maxDist};
    }

}

