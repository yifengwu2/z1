package com.c;

import java.util.ArrayList;
import java.util.List;

public class Solution32 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backed(nums, 0, results, path);

        return results;
    }

    private void backed(int[] nums, int start, List<List<Integer>> results, List<Integer> path) {
        //进入每一层，当前子集就是合法的
        results.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            //作出选择
            path.add(nums[i]);

            //回溯
            backed(nums, i + 1, results, path);

            //撤销
            path.remove(path.size() - 1);


        }
    }
}
