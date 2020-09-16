//
// Created by adrian on 16.09.2020.
//

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WOL {
    public void wake(byte[] bytes){
        try {
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.0.255"), 9);
            DatagramSocket socket = new DatagramSocket();

            socket.send(packet);
            socket.close();

            System.out.println("Magic packet send!");
        } catch (Exception e) {
            System.out.println("Filed to send magic packet: " + e);
            System.exit(1);
        }
    }
}