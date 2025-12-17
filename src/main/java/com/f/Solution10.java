package com.f;

public class Solution10 {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int count = 0;
        int left = 0;
        int right = n - 1;

        int defaultA = capacityA;
        int defaultB = capacityB;

        while (left <= right) {
            if (left == right) {
                if (capacityA >= capacityB) {
                    if (capacityA < plants[left]) {
                        count++;
                    }
                } else {
                    if (capacityB < plants[right]) {
                        count++;
                    }
                }
                break;
            }
            int waterA = plants[left];
            if (capacityA < plants[left]) {
                capacityA = defaultA;
                count++;
            }
            capacityA -= waterA;


            if (capacityB < plants[right]) {
                capacityB = defaultB;
                count++;
            }
            int waterB = plants[right];
            capacityB -= waterB;

            left++;
            right--;
        }

        return count;
    }
}
