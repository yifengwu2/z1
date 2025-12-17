package com.b;

public class Bank {
    private long[] balance;
    private int n;

    public Bank(long[] balance) {
        this.n = balance.length;
        this.balance = new long[n];
        // 复制数组，防止外部篡改
        System.arraycopy(balance, 0, this.balance, 0, n);
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 < 0 || account1 > n || account2 < 0 || account2 > n) return false;

        if (balance[account1 - 1] >= money) {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }

        return false;//余额不足
    }

    public boolean deposit(int account, long money) {
        if (account > 0 && account <= n) {
            for (int i = 0; i < n; i++) {
                if (account == i + 1) {
                    balance[i] += money;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(int account, long money) {
        if (account > 0 && account <= n) {
            for (int i = 0; i < n; i++) {
                if (account == i + 1) {
                    if (balance[i] >= money) {
                        balance[i] -= money;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
