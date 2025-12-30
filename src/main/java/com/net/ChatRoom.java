package com.net;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.*;

public class ChatRoom {
    public static void main(String[] args) throws IOException, InterruptedException {
        ChatRoom room = new ChatRoom();
        CountDownLatch count = new CountDownLatch(1);
        Receive receive = new Receive("receive-Thread", 10087);
        Server server = new Server(10086, "serve-Thread", 10087);
        room.asynImpl(receive, server);
    }

    private final ExecutorService pool = Executors.newFixedThreadPool(2);
    private final CountDownLatch latch = new CountDownLatch(1);

    public void asynImpl(Runnable r1, Runnable r2) {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(r1, pool);
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(r2, pool);
        CompletableFuture.allOf(f1, f2).thenRun(() -> {
            System.out.println("启动成功：发送端与接收端均已就绪");
            latch.countDown(); // 如果你还想用 latch，这里统一触发一次（可选）
            shutdown();
        }).exceptionally(ex -> {
            System.err.println("启动异常：" + ex.getMessage());
            shutdown();
            return null;
        });
    }

    public void shutdown() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    //发送端
    public boolean server(Runnable r) {
        Thread t = new Thread(r);
        t.start();
        return true;
    }

    //接收端
    public boolean receive(Runnable r) {
        Thread t = new Thread(r);
        t.start();
        return true;
    }
}

class Server implements Runnable {
    //本机监听端口
    private int port;
    //要给目标发的端口
    private int targetPort;
    //服务端名称
    private String name;
    //传输协议
    private final DatagramSocket ds;

    //默认给自己发
    public Server(int port, String name) throws SocketException {
        this(port, name, port);
    }

    public Server(int port, String name, int targetPort) throws SocketException {
        this.port = port;
        this.name = name;
        this.targetPort = targetPort; // ← 记住目标端口
        this.ds = new DatagramSocket(port); // 绑定自己端口
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        InetAddress address = null;
        try {
            address = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("请输入聊天内容...");
        while (true) {
            String s = sc.nextLine();
            byte[] bytes = s.getBytes();

            DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, address, targetPort);

            try {
                ds.send(dp);
                System.out.println("发送成功");

                if (s.equals("exit")) {
                    System.out.println("退出成功");
                    break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        close();
    }

    public void close() {
        ds.close();
        System.out.println("资源释放成功");
    }
}

class Receive implements Runnable {
    private String name;
    private int port;
    private final DatagramSocket ds;

    public Receive(String name, int port) throws SocketException {
        this.name = name;
        this.port = port;
        this.ds = new DatagramSocket(port);
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length);
            try {
                ds.receive(dp);
                byte[] data = dp.getData();
                int length = dp.getLength();
                System.out.println("接收到的内容是：" + new String(data, 0, length));
                if (new String(data, 0, length).equals("exit")) {
                    System.out.println("接收方退出....");
                    break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        close();

    }

    public void close() {
        ds.close();
    }
}
