package com.a;

import java.util.Arrays;
import java.util.HashSet;

public class Solution06 {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        nums[0] -= k;
        int ans = 1;
        for (int i = 1, top; i < nums.length; i++) {
            top = nums[i] + k;//记录上限

            if (nums[i] - k > nums[i - 1]) {//前一个值不在范围内
                nums[i] -= k;
                ans++;

            } else if (nums[i - 1] + 1 <= top) {
                nums[i] = nums[i - 1] + 1;
                ans++;
            }else {
                //因为已经排序了，说明此时nums[i - 1] + 1 > top
                //并且操作前一定和当前元素相同，该类值已经全部占位，直接赋值最大即可
                nums[i] += k;
            }
        }
        return ans;
    }
}
