package com.k;

import java.util.Arrays;

/**
 * 目标：找出所有大于 4 的不同数字，按降序排列，取前 3 个，返回数组。
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 3, 9, 1, 7};
        int[] array = Arrays.stream(nums)
                .filter(x -> x > 4)
                .boxed()
                .sorted((a, b) -> b - a)
                .limit(3)
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(Arrays.toString(array));

    }
}
