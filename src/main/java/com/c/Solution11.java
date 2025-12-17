package com.c;

import java.util.Objects;


public class Solution11 {
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        int n1 = split1.length;
        int n2 = split2.length;

        int len = Math.max(n1, n2);

        //Integer.parseInt() 会自动处理！
        for (int i = 0; i < len; i++) {
            //超出索引，视为0
            int num1 = i < n1 ? Integer.parseInt(split1[i]) : 0;
            int num2 = i < n2 ? Integer.parseInt(split2[i]) : 0;

            if (num1>num2){
                return 1;
            }else if (num1<num2){
                return -1;
            }

        }

        return 0;
    }
}
