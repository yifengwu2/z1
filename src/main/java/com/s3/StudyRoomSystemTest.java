package com.s3;


import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;


public class StudyRoomSystemTest {
    public static void main(String[] args) throws InterruptedException {
        StudyRoomSystem studyRoomSystem = new StudyRoomSystem(5, (auto, count) -> () -> {
            int stuId = auto.getAndIncrement();
            boolean success = StudyRoomSystem.lock(1, stuId);
            System.out.println("stu:" + stuId + "lock:" + success);
            count.countDown();
        });
        studyRoomSystem.execute();

    }
}

class StudyRoomSystem {
    private final static ConcurrentHashMap<Integer, Seat> seats = new ConcurrentHashMap<>();
    private final Runnable runnable;
    private final CountDownLatch countDownLatch;
    //(学生数)线程数
    private final int size;

    public StudyRoomSystem(int threadSize, BiFunction<AtomicInteger, CountDownLatch, Runnable> biFunction) {
        this.size = threadSize;
        AtomicInteger atomicInteger = new AtomicInteger(1001);
        this.countDownLatch = new CountDownLatch(threadSize);
        this.runnable = biFunction.apply(atomicInteger, countDownLatch);
    }

    public void execute() throws InterruptedException {
            for (int i = 0; i < size; i++) {
                Thread t = new Thread(runnable);
                t.start();
            }
            boolean await = countDownLatch.await(3, TimeUnit.SECONDS);
            if (await) {
                System.out.println("所有任务已完成");
            } else {
                System.out.println("超时，有任务卡住");
            }

    }

    private static final ScheduledExecutorService cleaner =
            Executors.newSingleThreadScheduledExecutor((r -> {
                Thread t = new Thread(r, "Cleanup-Thread");
                t.setDaemon(true);
                return t;
            }));


    //生成100个座位
    static {
        for (int i = 1; i <= 100; i++) {
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
        return compute != null && compute.getStatus() == Status.LOCKED && compute.getStuId() == stuId;
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
        return compute != null && compute.getStatus() == Status.Full;
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
