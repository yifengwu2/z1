package com.k3;

import java.util.*;

public class Solution19 {
    public int[] sortByReflection(int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int num : nums) {
            StringBuilder sb = new StringBuilder();

            int x = num;
            while (x != 0) {
                int i = x % 2;
                sb.append(i);
                x /= 2;
            }
            if (sb.length() == 0) {
                sb.append('0');
            }
            String s = sb.toString();
            //十进制
            int element = Integer.parseInt(s, 2);
            list.add(new int[]{num, element});
        }

        list.sort((res1, res2) -> {
            if (res1[1] != res2[1]) {
                return res1[1] - res2[1];
            }
            return res1[0] - res2[0];
        });
        int[] res = new int[nums.length];
        int j = 0;
        for (int[] ans : list) {
            res[j++] = ans[0];
        }
        return res;

    }
}
