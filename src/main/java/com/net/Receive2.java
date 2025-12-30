package com.net;

import java.net.SocketException;

public class Receive2 {
    public static void main(String[] args) throws SocketException {
        ChatRoom room = new ChatRoom();
        Receive receive = new Receive("receive-Thread", 10087);
        room.receive(receive);
    }
}
