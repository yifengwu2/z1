package com.f;

import java.util.ArrayList;
import java.util.List;

public class Solution24 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        int x = 0;
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            x = (x * 2 + nums[i]) % 5;
            if (x == 0) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }

}
