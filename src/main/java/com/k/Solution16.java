package com.k;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution16 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int slow = 0;
        int count0 = 0;
        int count1 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            }
        }


        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] == 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for (; slow < count0 + count1; slow++) {
            nums[slow] = 1;
        }

        for (int i1 = slow; i1 < n; i1++) {
            nums[i1] = 2;
        }
    }

}
