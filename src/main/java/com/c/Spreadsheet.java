package com.c;

import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {
    Map<String, Integer> map=new HashMap<>();

    public Spreadsheet(int rows) {
    }

    public void setCell(String cell, int value) {
        map.put(cell, value);
    }

    public void resetCell(String cell) {
        map.put(cell, 0);

    }

    public int getValue(String formula) {
        String[] split = formula.trim().split("[=+]");
        int sum = 0;
        for (String s : split) {
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            char[] chars = s.toCharArray();
            boolean flag = false;
            for (char c : chars) {
                if (!Character.isDigit(c)) {
                    flag = true;
                }
            }
            if (!flag) {
                int num = Integer.parseInt(s);
                sum += num;
            } else {
                Integer integer = map.getOrDefault(s, 0);
                sum += integer;
            }
        }

        return sum;
    }

}
