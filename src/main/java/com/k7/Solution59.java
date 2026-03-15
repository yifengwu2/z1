package com.k7;

public class Solution59 {
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int temp = nums[i];
            int j = i;
            while (j >= 0 && nums[j] < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            j++;
            nums[j] = temp;
        }
    }
}
