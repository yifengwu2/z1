package com.c;

public class Solution22 {
    public int minCost(String colors, int[] neededTime) {
        char[] chars = colors.toCharArray();
        int n = chars.length;

        int sum=0;

        int left=0;
        while (left<n){
            int right=left+1;

            int prev=left;

            while (right<n&&chars[prev]==chars[right]){
                // 贪心：保留 cost 更大的那个
                if (neededTime[prev]<neededTime[right]){
                    // prev 的 cost 更小，不值得保留，换成 right
                    //如果prev比right大，选比他小的继续和下一个比较
                    sum+=neededTime[prev];
                    prev=right;
                }else {
                    sum+=neededTime[right];
                }

                right++;
            }

            left=right;

        }

        return sum;

    }
}
