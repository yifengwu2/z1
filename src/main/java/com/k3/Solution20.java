package com.k3;

import java.util.ArrayList;
import java.util.List;

public class Solution20 {
    public int largestPrime(int n) {
        if (n == 1) return 0;
        if (n == 2) return 2;
        int count = 0;
        List<Integer> list = countPrime(n);
        if (list.isEmpty()) return 0;

        count += list.get(0);
        int sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (count + list.get(i) > n) break;
            count += list.get(i);
            if (isPrime(count)) sum = count;
        }
        return sum;
    }

    private List<Integer> countPrime(int n) {
        if (n < 2) return new ArrayList<>();
        boolean[] notPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {
                primes.add(i);
                if ((long) i * i <= n) {
                    for (int j = i * i; j <= n; j += i) {
                        notPrime[j] = true;
                    }
                }
            }
        }
        return primes;
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


