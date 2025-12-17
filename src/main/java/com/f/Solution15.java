package com.f;

public class Solution15 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] ans = new int[2];
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int num1 = numbers[left];
            int num2 = numbers[right];
            if (num1 + num2 > target) {
                right--;
            } else if (num1 + num2 < target) {
                left++;
            } else {
                ans[0] = left + 1;
                ans[1] = right + 1;
                break;
            }
        }
        return ans;
    }
}
