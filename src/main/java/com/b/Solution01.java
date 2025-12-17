package com.b;

import java.util.*;

public class Solution01 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int maxFreq = 1;//至少有一个数

        // 枚举每个 nums[i] 作为“目标值 val”
        for (int i = 0; i < n; i++) {
            int val = nums[i];

            //找出所有满足val-k<=num[j]<=val+k的下标j
            //即在这个范围内的数都可以变为目标值

            int left = leftBound(nums, val - k);//第一个>=val-k的位置
            int right = rightBound(nums, val + k) - 1;//最后一个<=val+k的位置

            // 没有数在这个范围内
            if (left > right) continue;

            int totalCount = right - left + 1; // 总共有多少个数可以变成 val

            //计算需要多少次操作
            // 原本就是 val 的数不需要操作
            int operationsNeeded = 0;
            for (int j = left; j < right; j++) {
                if (nums[j] != val) {
                    operationsNeeded++;
                }

            }
            //如果操作次数够用
            if (operationsNeeded < numOperations) {
                maxFreq = Math.max(maxFreq, totalCount);
            }
        }
        return maxFreq;
    }

    // 二分查找：第一个 >= target 的位置
    private int leftBound(int[] nums, int target) {
        int len = nums.length;
        int ll = 0;
        int rr = len;

        while (ll < rr) {
            int mid = ll + (rr - ll) / 2;

            if (nums[mid] < target) {
                ll = mid + 1;
            } else {
                rr = mid;
            }
        }
        return ll;
    }

    //找第一个>target的位置
    private int rightBound(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
