package com.d;

public class Solution05 {
    public int minMoves(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int num : nums) {
            int i1 = max - num;
            count += i1;
        }
        return count;
    }
}
