package com.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution11 {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (num > 0 && num % k == 0) {
                list.add(num);
            }
        }
        Collections.sort(list);

        int target = k;

        for (int num : list) {
            if (num == target) {
                target += k;
            } else if (num > target) {
                return target;
            }

        }

        return target;
    }
}
