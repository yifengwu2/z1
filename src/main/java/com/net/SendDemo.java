package com.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class SendDemo {
    public static void main(String[] args) throws IOException {
        //随机端口发出
        DatagramSocket ds = new DatagramSocket();

        //打包
        String s = "你好啊";
        byte[] bytes = s.getBytes();
        int port = 10086;
        InetAddress address = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, address, port);

        //发送
        ds.send(dp);

        //关闭
        ds.close();

    }
}
