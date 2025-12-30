package com.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        //快递公司
        DatagramSocket ds = new DatagramSocket(10086);

        //创建包裹接收
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length);

        ds.receive(dp);

        int port = dp.getPort();
        int length = dp.getLength();
        byte[] data = dp.getData();
        InetAddress address = dp.getAddress();
        System.out.println("接收到的数据是：" + new String(data, 0, length));
        System.out.println("ip地址是:" + address + "端口号是：" + port);

        ds.close();

    }
}
