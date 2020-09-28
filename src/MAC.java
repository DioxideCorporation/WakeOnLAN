//
// Created by adrian on 16.09.2020.
//

import java.util.Scanner;

public class MAC {

    public static byte[] mac(){
        final Scanner scan = new Scanner(System.in);
        byte[] MAC_Bytes;

        System.out.print("Please enter the MAC address of your network card: ");
        final byte[] MAC = getBytes(scan.next());

        MAC_Bytes = new byte[6 + 16 * MAC.length];
        for (int i = 0; i < 6; i++) {
            MAC_Bytes[i] = (byte) 0xFF;
        }
        for (int i = 6; i < MAC_Bytes.length; i += MAC.length) {
            System.arraycopy(MAC, 0, MAC_Bytes, i, MAC.length);
        }
        scan.close();
        return MAC_Bytes;
    }

    public static byte[] getBytes(final String MAC) throws IllegalArgumentException {
        final byte[] MAC_Bytes = new byte[6];
        final String[] hex = MAC.split("(\\:|\\-)");

        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address!");
        }
        try {
            for (int i = 0; i < 6; i++) {
                MAC_Bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address!");
        }
        return MAC_Bytes;
    }
}