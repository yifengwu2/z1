package com.d;

public class Solution25 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count = 0;
        int count_one = 0;
        int m = 0;
        // 1. 统计 1 的个数
        for (int num : nums) {
            if (num == 1) {
                count_one++;
            }
        }
        // 如果已经有 1，只需要把剩下的 n - countOnes 个数变成 1
        if (count_one > 1) {
            return n - count_one;
        }
        // 2. 检查整个数组的 GCD 是否为 1
        int totalGcd = nums[0];
        for (int i = 1; i < n; i++) {
            totalGcd = gcd(totalGcd, nums[i]);
        }
        if (totalGcd != 1) {
            return -1;
        }
        // 3. 找最短的连续子数组，其 GCD 为 1
        int minLen = n;
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break; // 再往后延伸 GCD 还是 1，但长度只会更长，不用继续
                }
            }
        }

        // minLen 长度的子数组需要 minLen - 1 次操作才能出一个 1
        // 然后用这个 1 去改剩下的 n-1 个数，需要 n-1 次操作
        return (minLen - 1) + (n - 1);

    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
