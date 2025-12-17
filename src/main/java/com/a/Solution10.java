package com.a;

public class Solution10 {
    public String reverseMessage(String message) {
        String[] strs = message.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            String s = strs[i];
            sb.append(s);
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
