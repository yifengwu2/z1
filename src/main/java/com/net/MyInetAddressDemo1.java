package com.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        //获取InetAddress对象
        //ip对象 一台电脑对象
        InetAddress address = InetAddress.getByName("192.168.1.100");
        System.out.println(address);

        String name = address.getHostName();
        System.out.println(name);

        String ip = address.getHostAddress();
        System.out.println(ip);

    }
}
