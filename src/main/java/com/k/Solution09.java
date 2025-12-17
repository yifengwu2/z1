package com.k;

import java.util.ArrayList;
import java.util.List;

public class Solution09 {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int slow = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num % 2 != 0) {
                list.add(num);
            }
        }
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] % 2 == 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        int j = 0;
        for (int i = slow; i < n; i++) {
            nums[i] = list.get(j++);
        }
        return nums;
    }
}
