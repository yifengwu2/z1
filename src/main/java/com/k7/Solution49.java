package com.k7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Solution49 {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        if (n == 1) {
            if (Objects.equals(nums[0], "1")) {
                return "0";
            } else {
                return "1";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = nums[i];
            char c = s.charAt(i);
            int i1 = c - '0';
            if (i1 == 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }
        return sb.toString();


    }
}
