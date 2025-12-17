package com.c;

import java.util.ArrayList;
import java.util.List;

public class Solution31 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        int n = nums.length;
        boolean[] used = new boolean[n];
        List<Integer> path = new ArrayList<>();

        backed(nums, used, results, path);

        return results;

    }

    private void backed(int[] nums, boolean[] used, List<List<Integer>> results, List<Integer> path) {
        if (nums.length == path.size()) {
            results.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {//剪枝：跳过已使用的
                continue;
            }
            //做出选择
            used[i] = true;
            path.add(nums[i]);

            backed(nums, used, results, path);

            //撤销
            path.remove(path.size() - 1);
            used[i] = false;

        }


    }


}
