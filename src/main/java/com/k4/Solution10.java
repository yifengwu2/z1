package com.k4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最大距离 = max(
 * 「其他数组的最大末元素 − 本数组最小首元素」（本数组提供小数），
 * 「其他数组的最小首元素 − 本数组最大末元素」（本数组提供大数）
 */
public class Solution10 {
    public int maxDistance(List<List<Integer>> arrays) {
        int n = arrays.size();
        int min = arrays.get(0).get(0);
        int maxLen = 0;
        //下标，最小值
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arrays.size(); i++) {
            min = Math.min(min, arrays.get(i).get(0));
            int v = arrays.get(i).get(0);
            map.put(i, v);
        }
        //找最小值下标
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == min) {
                index = entry.getKey();
            }
        }
        //如果key对应的value是最小值，那么这个跳过
        for (int i = 0; i < n; i++) {
            List<Integer> list = arrays.get(i);
            if (i == index) {
                continue;
            }
            maxLen = Math.max(maxLen, list.get(list.size() - 1) - min);
        }

        int maxLast = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < n; i++) {
            int last = arrays.get(i).get(arrays.get(i).size() - 1);
            if (last > maxLast) maxLast = last;
        }

        //新增：用 maxLast 配其他数组的首元素
        for (int i = 0; i < n; i++) {
            if (i == index) continue;
            int first = arrays.get(i).get(0);
            maxLen = Math.max(maxLen, maxLast - first);
        }

        return maxLen;
    }
}
