package com.e;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution06 {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            int fruit = fruits[right];
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            while (map.size() > 2) {
                Integer count = map.get(fruits[left]);
                count--;
                if (count == 0) {
                    map.remove(fruits[left]);// 数量为0，才真正移除类型
                }else {
                    map.put(fruits[left], count);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }
}
