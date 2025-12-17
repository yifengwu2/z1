package com.e;

public class Solution17 {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        char[] chars = s.toCharArray();
        int count_one = 0;
        String result = "";
        for (int right = 0; right < n; right++) {
            char c = chars[right];
            if (c == '1') {
                count_one++;
            }
            while (count_one > k) {
                if (chars[left] == '1') {
                    count_one--;
                }
                left++;
            }

            if (count_one == k) {
                //跳过左边的0，得到紧凑表示
                int i = left;
                while (i <= right && chars[i] == '0') {
                    i++;
                }
                if (i > right) continue;
                String candidate = s.substring(i, right + 1);

                //更新最优解
                if (result.isEmpty()) {
                    result = candidate;
                } else {
                    if (candidate.length() < result.length()) {
                        result = candidate;
                    } else if (candidate.length() == result.length()) {
                        int cmd = result.compareTo(candidate);
                        if (cmd > 0) {
                            result = candidate;
                        }
                    }
                }
            }
        }
        return result;
    }
}
