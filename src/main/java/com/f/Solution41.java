package com.f;

import java.util.*;

public class Solution41 {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (set.size() == 1 && nums.length == 1) return 1;
        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        int k = 0;
        int cnt = 0;
        while (k < res.length) {
            if (set.contains(res[k])) {
                cnt++;
                while (k < res.length && set.contains(res[k])) {
                    k++;
                }
            }else {
                k++;
            }
        }
        return cnt;
    }
}
