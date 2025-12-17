package com.k3;

public class Solution05 {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if (n == 1) return 1;
        int maxLen = 1;
        int left = 0;
        for (int right = 1; right < n; right++) {
            int diff = arr[right] - arr[right - 1];// 当前相邻差

            // 如果相等，湍流断开 → 新窗口从 right 开始（单元素）
            if (diff == 0) {
                left = right;
            }
            else if (right >= 2 &&
                    Integer.signum(diff) == Integer.signum(arr[right-1] - arr[right-2])) {
                left = right - 1;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }
}
