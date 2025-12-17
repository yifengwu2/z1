package com.f;

public class Solution08 {
    public boolean isPalindrome(String s) {
        int n = s.length();
        if (n == 1) return true;
        StringBuilder sb = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        s = sb.toString().toLowerCase();

        int len = s.length();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = len - 1;

        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
