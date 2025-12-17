package com.a;

import java.util.HashMap;
import java.util.Map;

public class Solution14 {
    public int finalValueAfterOperations(String[] operations) {
        Map<String, Integer> map = Map.of("++X", 1,
                "X++", 1,
                "--X", -1,
                "X--", -1);
        int x = 0;
        for (String operation : operations) {
            int i = map.get(operation);

            x += i;
        }

        return x;
    }
}
