package com.l;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 编写程序从键盘接受任意6个数，假设这6个数为138756，则要求输出一个具有如下形式的方阵
 */
public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入6位数字:\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String s = sc.nextLine();
            sb.append(s);
        }
        String s = sb.toString();
        String str = s + s;
        int[][] res = new int[6][6];
        for (int i = 0; i < 6; i++) {
            int k = i;
            for (int j = 0; j < 6; j++) {
                res[i][j] = Integer.parseInt(String.valueOf(str.charAt(k++)));
            }
            System.out.println(Arrays.toString(res[i]));
        }

    }
}
