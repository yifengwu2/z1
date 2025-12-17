package com.k;

import java.util.ArrayList;
import java.util.List;

public class Solution10 {
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        boolean tag = false;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int num : nums) {
            if (num % 2 == 0) {
                list1.add(num);
            } else {
                list2.add(num);
            }
        }
        int slow = 0;
        int z = 0;
        int l = 0;
        while (slow < n) {
            if (!tag) {
                if (!list1.isEmpty()) {
                    nums[slow] = list1.get(z);
                    z++;
                    tag = true;
                }
            } else {
                if (!list2.isEmpty()) {
                    nums[slow] = list2.get(l);
                    l++;
                    tag = false;
                }
            }
            slow++;

        }
        return nums;
    }
}
