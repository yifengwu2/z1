package com.k5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Solution18 {
    public int maximumSum(int[] nums) {
        List<Integer> l0 = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int num : nums) {
            int i = num % 3;
            if (i == 0) {
                l0.add(num);
            } else if (i == 1) {
                l1.add(num);
            } else if (i == 2) {
                l2.add(num);
            }
        }
        Collections.sort(l0);
        Collections.sort(l1);
        Collections.sort(l2);
        return 0;



    }
}
