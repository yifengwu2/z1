package com.k7;

public class Solution53 {
    public double[] convertTemperature(double celsius) {
        double[] ans = new double[2];
        double k = celsius + 273.15;
        double h = celsius * 1.80 + 32.00;
        ans[0] = k;
        ans[1] = h;
        return ans;
    }
}
