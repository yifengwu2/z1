package com.k;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution02 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> list = new ArrayList<>();//存所有三个数的和（去重复后）

        // 初始化一个结果，先假设前三个数是最优解
        int res = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 1; i++) {
            int a = nums[i];

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[left] + a + nums[right];
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}
