package com.k7;

public class Solution41 {
    public int balancedString(String s) {
        int n = s.length();
        int num = n / 4;
        int[] t = new int[128];
        for (char c : s.toCharArray()) {
            t[c]++;
        }
        int[] res = new int[128];
        int mis=0;
        for (char c : "QREW".toCharArray()) {
            res[c] = Math.max(0, t[c] - num);
            if (res[c]>0) mis++;
        }
        if (mis==0) return 0;

        int[] cnt = new int[128];
        int left = 0, ans = n;

        for (int right = 0; right < n; right++) {
            char cr = s.charAt(right);
            cnt[cr]++;

            if (cnt[cr] == res[cr]) {
                mis--;
            }

            while (mis == 0 && left <= right) {
                ans = Math.min(ans, right - left + 1);
                char cl = s.charAt(left);
                if (cnt[cl] == res[cl]) {
                    mis++;
                }
                cnt[cl]--;
                left++;
            }
        }

        return ans;


    }
}
