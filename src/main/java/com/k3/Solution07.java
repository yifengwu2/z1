package com.k3;

import java.util.ArrayList;
import java.util.List;

public class Solution07 {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        int i = 0;
        List<String> list = new ArrayList<>();

        while (i < n) {
            int j = i;
            StringBuilder sb = new StringBuilder();
            while (j + 1 < n && nums[j + 1] - nums[j] == 1) {
                j++;
            }
            if (j - i >= 1) {
                sb.append(nums[i]);
                sb.append("->");
                sb.append(nums[j]);
            } else {
                sb.append(nums[j]);
            }
            list.add(sb.toString());
            i = j + 1;
        }
        return list;
    }
}
