package com.k;

import java.util.Arrays;

public class Solution08 {
    public int[] maxKDistinct(int[] nums, int k) {
       return Arrays.stream(nums)
               .distinct()
               .boxed()
               .sorted((a,b)->b-a)
               .limit(k)
               .mapToInt(Integer::intValue).toArray();

    }
}
