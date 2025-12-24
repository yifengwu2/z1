package com.s3;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CompletableFutureTest {
    private final ExecutorService pool = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setName("t1");
        t.setDaemon(true);
        return t;
    });
    private final ConcurrentHashMap<Integer, User1> map = new ConcurrentHashMap<>();

    //因为 thenApplyAsync 的 lambda 是 Function<T,R>，它接受实物，返回实物（然后自动包成 CF<R>）。
    //而 thenComposeAsync 是 Function<T, CompletionStage<R>>，它接受实物，但强制你返回新单号（CF<R>）。
    public void asyn(User1 user) {
        int userId = user.getUserId();
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return save(user);
        }, pool).thenComposeAsync(user1 -> {
            User1 send = send(user1);
            return CompletableFuture.completedFuture(send);
        }, pool).thenAccept(v -> {
            log.info(v.getEmail());
        }).exceptionally(e -> {
            log.error("{}", e.getMessage());
            return null;
        });

    }

    public User1 save(User1 user) {
        int userId = user.getUserId();
        User1 compute = map.compute(userId, (Id, v) -> {
            if (user != null && user.getStatus() != Status.IDLE) {
                return v;
            }
            return new User1(userId, "", Instant.now().toString());
        });
        return compute;

    }

    public User1 send(User1 user) {
        int userId = user.getUserId();
        User1 compute = map.compute(userId, (Id, v) -> {
            if (user != null && user.getStatus() != Status.IDLE && user.getEmail() != "") {
                return v;
            }
            return new User1(userId, "@123", Instant.now().toString());
        });
        return compute;

    }

}

class User1 {
    private final int userId;
    //邮箱
    private final String email;
    private final String timeout;
    private Status status;

    public User1(int userId, String admin, String timeout) {
        this.userId = userId;
        this.email = admin;
        this.timeout = timeout;
    }

    public int getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User1{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", timeout='" + timeout + '\'' +
                ", status=" + status +
                '}';
    }
}
