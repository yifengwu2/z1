package com.f;

import java.util.Arrays;
import java.util.List;

public class Solution18 {
    public int countPairs(List<Integer> nums, int target) {
        int count = 0;
        int[] ans = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            ans[i] = nums.get(i);
        }
        Arrays.sort(ans);
        int n = ans.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (ans[left] + ans[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
