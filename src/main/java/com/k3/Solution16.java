package com.k3;

public class Solution16 {
    public boolean completePrime(int num) {
        String s = String.valueOf(num);
        int n = s.length();

        for (int i = 1; i <= n; i++) {
            int pre = Integer.parseInt(s.substring(0, i));
            if (!isPrime(pre)) {
                return false;
            }
        }
        for (int i = n - 1; i >= 0; i++) {
            int end = Integer.parseInt(s.substring(i, n));
            if (!isPrime(end)) {
                return false;
            }
        }

        return true;

    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;

        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}


