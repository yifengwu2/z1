package com.s3;


import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;


public class StudyRoomSystemTest {
    public static void main(String[] args) throws InterruptedException {
        int stuCount = 50;
        StudyRoomSystem studyRoomSystem = new StudyRoomSystem(1, stuCount, null);

        for (int i = 0; i < stuCount; i++) {
            new Thread(() -> {
                studyRoomSystem.asynImpl(1);
            },"Student-"+(1001+i)).start();
        }
        studyRoomSystem.awaitAllTask();


    }
}

@Slf4j(topic = "StudyRoomSystem")
class StudyRoomSystem {
    private final static ConcurrentHashMap<Integer, Seat> seats = new ConcurrentHashMap<>();
//    private final Runnable runnable;
    private final CountDownLatch countDownLatch;
    //(学生数)线程数
//    private final int size;
    //异步单一线程池（一个线程）
    private final ExecutorService pool;
    private final AtomicInteger auto;
    //座位id
    private int seatId;
    //清理线程池
    private static final ScheduledExecutorService cleaner =
            Executors.newSingleThreadScheduledExecutor((r -> {
                Thread t = new Thread(r, "Cleanup-Thread");
                t.setDaemon(true);
                return t;
            }));


    public StudyRoomSystem(int seatId, int stuCount, BiFunction<AtomicInteger, CountDownLatch, Runnable> biFunction) {
//        this.size = threadSize;
        this.auto = new AtomicInteger(1001);
        this.countDownLatch = new CountDownLatch(stuCount);
//        this.runnable = biFunction.apply(auto, countDownLatch);
        this.pool = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread("t1");
            t.setDaemon(true);
            return t;
        });
        this.seatId = seatId;
    }

    public void asynImpl(int seatId) {
        if (seatId <= 0) {
            log.error("无效抢座{}", seatId);
            countDownLatch.countDown();
            return;
        }
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int stuId = auto.getAndIncrement();
            log.info("学生 {} 开始预约座位 1", stuId);
            if (lock(seatId, stuId) && sign(seatId, stuId)) {
                notifyStu();
                return true;
            }
            return false;
        }, pool).thenAccept(success -> {
            if (success) {
                log.info("学生成功预约并占位");
            } else {
                log.warn("预约失败（可能已被抢）");
            }
            //任务完成倒计时-1
            countDownLatch.countDown();
        }).exceptionally(ex -> {
            log.error("学生预约异常");
            countDownLatch.countDown();
            return null;
        });
    }

    public void awaitAllTask() throws InterruptedException {
        countDownLatch.await();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    //通知
    public void notifyStu() {
        System.out.println("成功预约并占位");
    }

    public void execute() {
//        for (int i = 0; i < size; i++) {
//            Thread t = new Thread(() -> {
//                try {
//                    runnable.run();
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//            t.start();
//        }
//
//        boolean await = countDownLatch.await(5, TimeUnit.SECONDS);
//        if (await) {
//            System.out.println("所有任务已完成");
//        } else {
//            System.out.println("超时，有任务卡住");
//        }
        asynImpl(seatId);


    }




    //生成100个座位
    static {
        log.debug("正在生成座位...");
        for (int i = 1; i <= 50; i++) {
            seats.put(i, new Seat(i));
        }
        //启动后每30秒执行一次任务
        cleaner.scheduleAtFixedRate(StudyRoomSystem::cleanupExpiredLocks,
                30, 30,
                TimeUnit.SECONDS);
    }

    //预约座位
    public static boolean lock(int seatId, int stuId) {
        Seat compute = seats.compute(seatId, (k, v) -> {
            long epochMilli = Instant.now().plusSeconds(30 * 60).toEpochMilli();
            if (v == null || v.getStatus() != Status.IDLE) {
                return v;
            }
            return new Seat(k, stuId, Status.LOCKED, epochMilli);
        });
        boolean success = compute != null && compute.getStatus() == Status.LOCKED && compute.getStuId() == stuId;
        if (success) {
            log.debug("预约成功，请尽快到达座位...");
        } else {
            log.debug("预约失败请重新预约");
        }
        return success;
    }

    public ConcurrentHashMap<Integer, Seat> getSeats() {
        return seats;
    }

    //现场签到
    public boolean sign(int seatId, int stuId) {
        Seat compute = seats.compute(seatId, (k, v) -> {
            if (v == null || v.getStatus() != Status.LOCKED || v.getStuId() != stuId || v.isExpired()) {
                return v;
            }
            return new Seat(k, stuId, Status.Full, 0);
        });
        boolean success = compute != null && compute.getStatus() == Status.Full;
        if (success) {
            log.debug("{}签到成功，当前状态{}", Thread.class.getSimpleName(), compute.getStatus());
        } else {
            log.debug("签到失败或已过期");
        }
        return success;
    }

    //清理预约过期座位
    private static void cleanupExpiredLocks() {
        seats.forEach((seatId, seat) -> {
            if (seat.getStatus() == Status.LOCKED && seat.isExpired()) {
                // 原子替换回空闲座位（IDLE）
                seats.replace(seatId, seat, new Seat(seatId));
                System.out.printf("[CLEAN] Released expired lock on seat %d%n", seatId);
            }
        });
    }
}


class Seat {
    //座位号
    private final int seatId;
    //学生id
    private final int stuId;
    //状态
    private final Status status;
    //过期时间戳
    private final long timeExpired;

    public Seat(int seatId) {
        this(seatId, 0, Status.IDLE, 0);
    }

    public Seat(int seatId, int stuId, Status status, long timeExpired) {
        this.seatId = seatId;
        this.stuId = stuId;
        this.status = status;
        this.timeExpired = timeExpired;
    }

    public boolean isExpired() {
        return timeExpired > 0 && Instant.now().toEpochMilli() > timeExpired;
    }


    public Status getStatus() {
        return status;
    }

    public int getStuId() {
        return stuId;
    }

    public int getSeatId() {
        return seatId;
    }
}
