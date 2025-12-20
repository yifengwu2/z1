package com.s2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test01 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        method1();
    }
    public static void method1() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = now.format(pattern);
        System.out.println(format);
    }
}
