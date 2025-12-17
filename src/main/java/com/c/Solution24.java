package com.c;

import java.util.*;

public class Solution24 {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        // 结果数组长度是 n - k + 1
        int[] answer = new int[n - k + 1];

        for (int i = 0; i < n - k+1; i++) {
            answer[i] = computeXsum(nums, i, i + k - 1, x);
        }
        return answer;

    }

    // 计算子数组 [left, right] 的 x-sum
    private int computeXsum(int[] nums, int left, int right, int x) {
        Map<Integer, Integer> freq = new HashMap<>();

        //统计频率
        for (int i = left; i <= right; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        // 获取所有键值对并排序
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> {
            // 先按频次降序，再按数值降序
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();// 频次高者在前
            }
            // 数值大者在前
            return b.getKey() - a.getKey();
        });
        // 取出前 x 个 key（要保留的元素）
        Set<Integer> topKeys = new HashSet<>();
        for (int i = 0; i < Math.min(x, entries.size()); i++) {
            Map.Entry<Integer, Integer> entry = entries.get(i);
            topKeys.add(entry.getKey());
        }
        // 累加这些 key 在子数组中所有出现的和
        int sum = 0;
        for (int i = left; i <= right; i++) {
            if (topKeys.contains(nums[i])) {
                sum += nums[i];
            }
        }

        return sum;
    }
}
