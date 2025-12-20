package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "TestSchedule")
public class TestSchedule {
    //如何让每周四18:00:00定时执行任务
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //获取周四时间
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        //如果 当前时间>本周周四，必须找到下周周四
        if (now.isAfter(time)) {
            time = time.plusWeeks(1);
        }
        System.out.println(time);
        //一周的时间间隔
        long period = 1000L * 60 * 60 * 60 * 24 * 7;
        //代表当前时间和周四时间的时间差
        long initialTime = Duration.between(now, time).toMillis();
        ;
        pool.scheduleAtFixedRate(() -> {
            log.debug("开始执行任务");
        }, initialTime, period, TimeUnit.MILLISECONDS);

    }
}
