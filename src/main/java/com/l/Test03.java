package com.l;

import java.util.*;

/**
 * 从键盘输入4个整数，按由小到大顺序排序
 */
public class Test03 {
    public static void main(String[] args) {
        int[] ans = new int[4];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入4个数，(连续4个)：");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(sc.nextInt());
        }
        for (int i = 0; i < 4; i++) {
            ans[i] = list.get(i);
        }

        int[] array = Arrays.stream(ans)
                .boxed()
                .sorted(Comparator.comparingInt(a -> a))
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println(Arrays.toString(array));

    }
}
