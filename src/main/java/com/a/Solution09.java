package com.a;

public class Solution09 {
    public String reverseWords(String s) {
        int len = s.length();

        String[] s1 = s.trim().split("\\s+");

        //倒序拼接
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            String s2 = s1[i];
            sb.append(s2);
            if (i > 0) {
                sb.append(" ");//最后一个不加空格
            }
        }


        return sb.toString();
    }
}
