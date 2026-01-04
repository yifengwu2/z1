package com.k6;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * 异步任务执行中心（带重试/超时/死信）
 */
public class ExecutionCenter {
    private final ScheduledExecutorService scheduled = new ScheduledThreadPoolExecutor(2, r -> new Thread(r, "retry-scheduler"));
    private final BlockingQueue<DeadLetter> blockingQueue = new LinkedBlockingDeque<>();

    public void submit(Command command) {
        scheduled.schedule(() -> {
            runCommand(command);
        }, 0, TimeUnit.NANOSECONDS);
    }

    private void runCommand(Command command) {
        try {
            if (command.execute()) {
                System.out.printf("[✓] 发券成功 | 用户:%d 券:%d%n", command.userId, command.couponId);
                return;
            }
        } catch (Exception e) {
            handleFailure(command, e);
        }
    }

    private void handleFailure(Command command, Throwable error) {
        RetryDecision decision = command.onFailure(error);
        switch (decision) {
            case DIE_IN_DEAD_LETTER:
                blockingQueue.offer(new DeadLetter(command, error, Instant.now()));
                System.err.printf("[☠] 死信入库 | 用户:%d 券:%d 错误:%s%n",
                        command.userId, command.couponId, error.getMessage());
                return;
//            case RETRY_AFTER(long delayMs):
//                scheduled.schedule(
//                        () -> runCommand(command.nextAttempt()),
//                        delayMs, TimeUnit.MILLISECONDS
//                );
//                break;

        }
    }

    public void shutdown() {
        scheduled.shutdown();
        try {
            if (!scheduled.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduled.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
            scheduled.shutdownNow();
        }
    }

    public BlockingQueue<DeadLetter> getDeadLetterQueue() {
        return blockingQueue;
    }
}

/**
 * 命令对象
 */
class Command {
    public final int userId;
    public final int couponId;
    public final int attempt;
    public final Instant startTime;


    public Command(int userId, int couponId, int attempt) {
        this.userId = userId;
        this.couponId = couponId;
        this.attempt = attempt;
        this.startTime = Instant.now();
    }

    public boolean execute() {
        System.out.printf("[→] 尝试发券 | 用户:%d 券:%d 第%d次%n", userId, couponId, attempt);
        return Math.random() > 0.1;
    }

    public Command nextAttempt() {
        return new Command(userId, couponId, attempt + 1);
    }

    public RetryDecision onFailure(Throwable error) {
        if (attempt >= 3) {
            return RetryDecision.DIE_IN_DEAD_LETTER;
        }
        // 指数退避：1s, 2s, 4s
        long delay = (long) Math.pow(2, attempt) * 1000;
        return RetryDecision.RETRY_AFTER(delay);
    }
}

/**
 * 重试决策枚举
 */
enum RetryDecision {
    DIE_IN_DEAD_LETTER,
    RETRY_AFTER;
    final long delayMs;

    RetryDecision(long delayMs) {
        this.delayMs = delayMs;
    }

    RetryDecision() {
        this.delayMs = 0;
    }

    public static RetryDecision RETRY_AFTER(long delay) {
        return RETRY_AFTER;
    }
}

//死信消息
class DeadLetter {
    private final Command command;
    private final Throwable erro;
    private final Instant stamp;

    public DeadLetter(Command command, Throwable erro, Instant timestamp) {
        this.command = command;
        this.erro = erro;
        this.stamp = timestamp;
    }
}


