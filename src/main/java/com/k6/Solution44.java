package com.k6;

public class Solution44 {
    public int sumFourDivisors(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            int num = nums[i];
            int sum = 0;

            int sqrt = (int) Math.sqrt(num);
            for (int j = 1; j <= sqrt; j++) {
                if (num % j == 0) {
                    cnt++;
                    sum += j;

                    int other = num / j;
                    if (other != j) {
                        sum += other;
                        cnt++;
                    }
                }
            }
            if (cnt == 4) {
                count += sum;
            }
        }
        return count;

    }
}
