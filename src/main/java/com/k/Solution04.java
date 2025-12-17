package com.k;

import java.util.HashSet;
import java.util.Set;

public class Solution04 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            // 如果发现一个和当前 slow 不同的数
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow+1;
    }
}
