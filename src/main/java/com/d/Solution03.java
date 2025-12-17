package com.d;

import java.util.*;

public class Solution03 {
    public int characterReplacement(String s, int k) {
        int n = s.length();

        int[] count = new int[26];
        int left = 0;
        int maxLen = 0;


        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            count[c - 'A']++;

            //更新当前窗口中出现最多的字符的频次
            int maxFreq = 0;
            for (int i = 0; i < 26; i++) {
                maxFreq = Math.max(maxFreq, count[i]);
            }

            //当前窗口总长度-频率最高的=需要改的数
            //如果改的数>k说明改不过来
            while (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
