package com.f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution21 {
    public int maxSumDivThree(int[] nums) {
        int count = 0;
        List<Integer> list1 = new ArrayList<>();//余数为1
        List<Integer> list2 = new ArrayList<>();//余数为2
        for (int num : nums) {
            if (num % 3 == 1) {
                list1.add(num);
            }
            if (num % 3 == 2) {
                list2.add(num);
            }
            count += num;
        }
        Collections.sort(list1);
        Collections.sort(list2);
        Integer remove = Integer.MAX_VALUE;
        if (count % 3 == 0) {
            return count;
        } else if (count % 3 == 1) {
            //选择删除余数为1的或者两个余数为2的

            if (!list1.isEmpty()) {
                remove = list1.get(0);
            }
            if (list2.size() > 2) {
                int res2 = list2.get(0) + list2.get(1);
                remove = Math.min(remove, res2);
            }

        } else if (count % 3 == 2) {
            //删除一个2或者两个1
            if (!list2.isEmpty()) {
                remove = list1.get(0);
            }
            if (list1.size() > 2) {
                remove = Math.min(remove, list1.get(0) + list1.get(1));
            }

        }
        return count-remove;
    }
}
