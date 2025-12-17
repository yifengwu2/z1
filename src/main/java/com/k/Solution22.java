package com.k;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution22 {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());

        List<Integer> list2 = Arrays.stream(nums2).boxed().filter(num -> {
            if (list.contains(num)) {
                list.remove(num);
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        return list2.stream().mapToInt(Integer::intValue).toArray();
    }
}
