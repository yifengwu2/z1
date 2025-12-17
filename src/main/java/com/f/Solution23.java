package com.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 尽管 prefixXor 的值是 3，而不是 0，但这完全不影响我们判断中间段异或为 0！
 * 只要两个位置的 prefixXor 相同，中间那段就一定是 0。
 * 就像：
 * 你从家走到公园再走回来，总位移是 0
 * 虽然你在中途某个时刻离家 3 公里远
 * 但只要你最后又回到了那个“离家 3 公里”的地方，说明中间走了个来回
 * prefixXor[j] = prefixXor[i] ^ (nums[i]^...^nums[j-1])
 * => 如果两者相等，则中间那段异或必须是 0
 */
public class Solution23 {
    public int maxBalancedSubarray(int[] nums) {
        int[] norivandal = nums;
        // 哈希表：key 是 (xor, balance)，value 是第一次出现该状态的索引
        Map<String, Integer> firstOccurrence = new HashMap<>();

        int prefixXor = 0;
        int balance = 0;
        int maxLength = 0;

        //初始状态在-1位置
        firstOccurrence.put("0,0", -1);

        for (int i = 0; i < norivandal.length; i++) {
            int num = norivandal[i];

            // 更新前缀异或
            prefixXor ^= num;

            // 更新平衡值：奇数 +1，偶数 -1
            if (num % 2 == 1) {
                balance++;
            } else {
                balance--;
            }
            String key = prefixXor + "," + balance;
            if (firstOccurrence.containsKey(key)) {
                Integer start = firstOccurrence.get(key);
                int len = i - start; // 子数组长度
                maxLength = Math.max(maxLength, len);
            } else {
                firstOccurrence.put(key, i);
            }
        }
        return maxLength;
    }
}
