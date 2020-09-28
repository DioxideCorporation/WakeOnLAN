//
// Created by adrian on 16.09.2020.
//

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WOL {
    public void wake(final byte[] bytes) {
        try {
            final DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
                    InetAddress.getByName("255.255.255.255"), 9);
            final DatagramSocket socket = new DatagramSocket();

            socket.send(packet);
            socket.close();

            System.out.println("Magic packet send!");
        } catch (final Exception e) {
            System.out.println("Filed to send magic packet: " + e);
            System.exit(1);
        }
    }
}