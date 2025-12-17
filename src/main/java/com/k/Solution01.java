package com.k;

import java.util.*;

public class Solution01 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int a = nums[i];
            int left = i + 1;
            int right = n - 1;

            //判断如果和上一个相等，跳过处理下一次
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //找到 [-2,0,2] 后，如果不跳过：
            //下一步 left++ → 还是 0
            //right-- → 还是 2
            //又找到 [-2,0,2] → 重复！
            while (left < right) {
                int b = nums[left];
                int c = nums[right];

                if (b + c == -a) {
                    List<Integer> list = Arrays.asList(a, b, c);
                    res.add(list);
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (b + c < -a) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return res;
    }
}
