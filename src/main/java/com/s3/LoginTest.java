package com.s3;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginTest {
    public static void main(String[] args) {
        LoginSystem system = new LoginSystem(4, 50);
        system.execute();

    }

}

class LoginSystem {
    private final static AtomicInteger userId = new AtomicInteger(1);
    //id，用户
    private final static ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    //异步单线程
    private final ExecutorService pool;
    //消费者
    private final int consumer;
    //请求数
    private final int size;
    private final CountDownLatch latch;
    private final ExecutorService threads;
    //阻塞队列
    private final BlockingQueue<User> blockingQueue;


    public LoginSystem(int consumer, int size) {
        this.pool = Executors.newFixedThreadPool(4);
        this.consumer = consumer;
        this.size = size;
        this.latch = new CountDownLatch(size);
        this.threads = Executors.newFixedThreadPool(consumer);
        this.blockingQueue = new ArrayBlockingQueue<>(100);
    }

    static {
        //初始化用户
        for (int k = 0; k < 50; k++) {
            int i = userId.getAndIncrement();
            users.put(i, new User(i, "user" + i, "123", Result.NotLOG, "0"));
        }
    }

    //模拟50个用户去查询一个账户
    public void execute() {
        User user = new User(1, "user1", "123", Result.NotLOG, "0");
        for (int i = 0; i < size; i++) {
            try {
                blockingQueue.put(user);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.err.println("请求投递被中断，已发送 " + i + " 个");
                break;
            }
        }
        conSumer();
        awaitAndClosed();
    }

    //消费任务
    public void conSumer() {
        for (int i = 0; i < consumer; i++) {
            threads.submit(() -> {
                while (Thread.currentThread().isInterrupted()) {
                    try {
                        User user = blockingQueue.poll(1, TimeUnit.SECONDS);
                        if (user != null) {
                            asynImpl(user);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });
        }

    }


    //thenApplyAsync(fn, exec) 是自动包装（隐式 CF<R>）；
    // thenCompose(fn) 要求你手动返回 CF<R>（显式），但它不是“为了让你麻烦”，而是为了——彻底消灭嵌套 CF<CF<R>>！
    //thenApplyAsync(fn, executor)
    //thenApplyAsync他这个是自动帮我们包了个CF？thenCompose就是要我们手动包装在传
    public void asynImpl(User user) {
        int id = user.getId();
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始查找用户");
            if (findUser(id)) {
                System.out.println("查询成功");

            }
            System.out.println("查询失败");
            return user;
        }, pool).thenApplyAsync(user1 -> {
            if (checkPassword(user1) && isLogin(user1)) {
                return true;
            } else {
                return false;
            }
        }, pool).thenAccept(b -> {
            if (b) {
                System.out.println("登陆成功");
            }
            System.out.println("登陆失败");
            latch.countDown();
        }).exceptionally(ex -> {
            System.out.println("过程中出错");
            System.out.println(ex.getMessage());
            latch.countDown();
            return null;
        });
    }

    public void awaitAndClosed() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("压测等待被中断: " + e.getMessage());
        }
        pool.shutdown();
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                System.err.println("异步池未及时关闭，已强制终止");
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        //顺便关闭压测线程池
        threads.shutdown();
        try {
            if (!threads.awaitTermination(3, TimeUnit.SECONDS)) {
                threads.shutdownNow();
            }
        } catch (InterruptedException e) {
            threads.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    //查找用户
    public boolean findUser(int id) {
        try {
            //模拟查找耗时
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        User user = users.get(id);
        return user != null;
    }

    //校验密码
    //：compute() 被误用为「读+写」，但它本质是「原子更新」——而你不需要更新！
    public boolean checkPassword(User user) {
        //模拟等待
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        int id = user.getId();
        User user1 = users.get(id);
        if (user1 == null) {
            System.out.println("用户为空");
            return false;
        }
        //判断两者密码
        boolean match = Objects.equals(user1.getPassword(), user.getPassword());
        if (match) {
            System.out.println("登陆成功" + user1.getName());
            return true;
        } else {
            System.out.println("密码错误" + user1.getId());
        }
        return false;

//        User compute = users.compute(id, (userId, user1) -> {
//            if (user1 == null || !Objects.equals(user1.getPassword(), user.getPassword())) {
//                //密码不想等的话，返回一个密码为""
//                //这里有错误吧，这里如果不等会把我map中存有正确密码的用户给改了，这咋办
//                return new User(id, "user" + id, "", Result.NotLOG, Instant.now().toString());
//            }
//            return new User(id, "user" + id, user1.getPassword(), Result.NotLOG, Instant.now().toString());
//        });
//        return !Objects.equals(compute.getPassword(), "");


    }

    //记录用户登陆成功
    public boolean isLogin(User user) {
        int id = user.getId();
        User compute = users.compute(id, (i, user1) -> {
            if (user1 == null || user1.getResult() == Result.LOG || !Objects.equals(user1.getPassword(), user.getPassword())) {
                return user1;
            }
            return new User(id, "user" + id, user1.getPassword(), Result.LOG, Instant.now().toString());
        });
        boolean success = compute != null && compute.getResult() != Result.NotLOG && Objects.equals(compute.getPassword(), user.getPassword());
        if (success) {
            System.out.println("用户" + compute.getName() + "登陆成功");
        }
        if (compute != null) {
            System.out.println("用户" + compute.getName() + "登陆失败");
        }
        return false;
    }


}

class User {
    private final int id;
    private final String name;
    private final String password;
    private final Result result;
    //操作时间
    private final String time;
    //时间戳
    private int timeout;


    public User(int id, String name, String password, Result result, String time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.result = result;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && timeout == user.timeout && Objects.equals(name, user.name) && Objects.equals(password, user.password) && result == user.result && Objects.equals(time, user.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, result, time, timeout);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
