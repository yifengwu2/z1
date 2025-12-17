package com.k4;

public class Solution03 {
    public int alternatingSubarray(int[] nums) {
        int n = nums.length;
        if (n == 2) return 2;
        int ans = -1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] != nums[i] + 1) {
                continue;
            }
            int j = i + 1;
            int diff = 1;

            while (j + 1 < n) {
                int expect = -diff;
                if (nums[j + 1] - nums[j] == expect) {
                    j++;
                    diff = -diff;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

}
