package com.b;

public class Solution08 {
    public int nextBeautifulNumber(int n) {
        int x = n + 1;
        while (true) {
            if (isBalance(x)) {
                return x;
            }
            x++;
        }
    }

    private boolean isBalance(int x) {
        //统计有几个位数的
        int[] count = new int[10];
        while (x > 0) {
            count[x % 10]++;
            x /= 10;
        }
        for (int d = 0; d < 10; d++) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;

    }


}

