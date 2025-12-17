package com.a;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution05 {
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = -k;

        int[] res = new int[n];

        System.arraycopy(nums, 0, res, 0, n);

//        if (n == 1) return 1;

        Set<Integer> set = new HashSet<>();

        res[0] = res[0] + l;
        set.add(res[0]);

        for (int i = 1; i < n; ) {

            int j = i;
            while (j < n && nums[i] == nums[j]) {
                j++;
            }

            // 对这一组相同的数，尝试用 l 从 -k 开始递增赋值
            l = -k; // 每组重新从 -k 开始（避免 l 越界）
            for (int idx = i; idx < j; idx++) {
                int target = nums[i] + l;
                /// 确保偏移在 [-k, k] 内
                if (l > k) {
                    break;
                }
                if (!set.contains(target)) {
                    res[idx] = target;
                    set.add(res[idx]);
                    l++;// 成功，尝试下一个偏移
                } else {
                    // 冲突了，尝试更大的偏移，idx 不变
                    l++;
                    idx--; // 重新尝试当前这个数
                }


            }
            i = j;//跳过这组
        }

        return set.size();
    }
}
