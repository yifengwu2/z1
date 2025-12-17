package com.b;

public class Solution11 {
    public String replaceSpaces(String S, int length) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') {
                count++;
            }
        }
        int countLen = count * 2;
        char[] res = new char[length + countLen];

        int n = length - 1;
        for (int i = res.length - 1; i >= 0; ) {
            if (n >= 0) {
                char c = S.charAt(n--);
                if (c == ' ') {
                    res[i--] = '0';
                    res[i--] = '2';
                    res[i--] = '%';
                } else {
                    res[i--] = c;
                }
            } else {
                break;
            }
        }
        return String.valueOf(res);

    }
}
