package com.c;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution20 {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // 按值排序，找 top3 和 bottom2 的索引
        Arrays.sort(indices, Comparator.comparingInt(a -> nums[a]));

        Set<Integer> critical = new HashSet<>(Arrays.asList(indices).subList(n - 3, n)); // top3
        critical.addAll(Arrays.asList(indices).subList(0, 2));// bottom2

        //枚举每个位置
        long max = Long.MIN_VALUE;
        for (int i : critical) {
            for (int x : new int[]{100000, -100000}) {
                int[] clone = nums.clone();
                clone[i] = x;

                long maxNum = findThirdNum(clone);
                max = Math.max(max, maxNum);
            }
        }
        return max;
    }

    private long findThirdNum(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;

        //最大的三个数
        long num1 = (long) arr[n - 1] * arr[n - 2] * arr[n - 3];
        //最小两个*最大一个
        long num2 = (long) arr[0] * arr[1] * arr[n - 1];

        return Math.max(num1, num2);

    }
}
