//
// Created by adrian on 16.09.2020.
//

import java.util.Scanner;

public class MAC {
    private byte[] MAC_Bytes;

    public byte[] mac(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the MAC address of your network card: ");
        byte[] MAC = getBytes(scan.next());

        MAC_Bytes = new byte[6 + 16 * MAC.length];
        for (int i = 0; i < 6; i++){
            MAC_Bytes[i] = (byte) 0xFF;
        }
        for (int i = 6; i < MAC_Bytes.length; i += MAC.length){
            System.arraycopy(MAC, 0, MAC_Bytes, i, MAC.length);
        }
        return MAC_Bytes;
    }

    private static byte[] getBytes(String MAC) throws IllegalArgumentException {
        byte[] MAC_Bytes = new byte[6];
        String[] hex = MAC.split("(\\:|\\-)");

        if (hex.length != 6){
            throw new IllegalArgumentException("Invalid MAC address!");
        }
        try {
            for (int i = 0; i < 6; i++){
                MAC_Bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid hex digit in MAC address!");
        }
        return MAC_Bytes;
    }

    public byte[] getMAC_Bytes() {
        return MAC_Bytes;
    }

    public void setMAC_Bytes(byte[] MAC_Bytes) {
        this.MAC_Bytes = MAC_Bytes;
    }
}