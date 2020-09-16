//
// Created by adrian on 16.09.2020.
//

import java.io.*;
import java.util.Scanner;

public class Main {
    public Main(){
        InputStream inFile = null;
        OutputStream outFile = null;
        Scanner scan = new Scanner(System.in);
        WOL wol = new WOL();
        int value;

        System.out.println("Welcome to the WakeOnLAN app, created by Adrian Herrmann");

        try {
            inFile = new FileInputStream("MAC_AddressList.mac");
            System.out.println("The MAC_AddressList.mac file was found!");
        } catch (FileNotFoundException e) {
            try {
                outFile = new FileOutputStream("MAC_AddressList.mac");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            System.out.println("The MAC_AddressList.mac file could not be found!");
            System.out.print("Creating a new file, please enter the number of computers to be added: ");
            value = scan.nextInt();
            try {
                outFile.write(intToByteArray(value));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            for (int i = 0; i < value; i++){
                System.out.print(i + 1 + "# ");
                try {
                    outFile.write(MAC.mac());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            try {
                outFile.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try {
                inFile = new FileInputStream("MAC_AddressList.mac");
            } catch (FileNotFoundException fileNotFoundException) {
                System.err.println("The MAC_AddressList.mac file could not be found!");
                fileNotFoundException.printStackTrace();
                System.exit(-1);
            }
        }
        try {
            int a = byteArrayToInt(reverse(inFile.readNBytes(4))), offset = 4;
            byte[] b = new byte[8192];

            for (int i = 0; i < a; i++){
                inFile.readNBytes(b, offset, 102);
                System.out.print(i + 1 + "# ");
                wol.wake(b);
                offset += 102;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Press any key: ");
        scan.nextLine();
    }

    public static void main(String args[]){
        new Main();
    }

    public static int byteArrayToInt(byte[] b)
    {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a)
    {
        byte[] ret = new byte[4];
        ret[0] = (byte) (a & 0xFF);
        ret[1] = (byte) ((a >> 8) & 0xFF);
        ret[2] = (byte) ((a >> 16) & 0xFF);
        ret[3] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    public static byte[] reverse(byte[] array) {
        if (array == null) {
            return null;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        return array;
    }
}