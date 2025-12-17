package com.c;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {
    //下标->数字
    private HashMap<Integer, Integer> map;
    // 新增：数字 -> 所有下标的有序集合（TreeSet 会自动排序）
    private Map<Integer, TreeSet<Integer>> numToIndices;

    public NumberContainers() {
        this.map = new HashMap<>();
        this.numToIndices = new HashMap<>();
    }

    public void change(int index, int number) {
        //如果这个下标原来有数字了，从numToIndices移除旧映射
        if (map.containsKey(index)) {
            Integer oldNumber = map.get(index);
            // 从旧数字的集合中移除这个下标
            TreeSet<Integer> set = numToIndices.get(oldNumber);
            if (set!=null){
                set.remove(index);
            }
        }
        //更新
        map.put(index, number);
        if (numToIndices.containsKey(number)) {
            numToIndices.get(number).add(index);
        } else {
            TreeSet<Integer> integers = new TreeSet<>();
            integers.add(index);
            numToIndices.put(number, integers);
        }

    }

    public int find(int number) {
        TreeSet<Integer> integers = numToIndices.get(number);

        if (integers==null||integers.isEmpty()) {
            return -1;
        }
        return integers.first();
    }
}
