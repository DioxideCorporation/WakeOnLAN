//
// Created by adrian on 16.09.2020.
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public Main() {
        ArrayList<MAC> MAC_AddressList = new ArrayList<>();
        File file = new File("MAC_AddressList.mac");
        PrintWriter write = null;
        Scanner scan = new Scanner(System.in);
        Scanner sFile;
        WOL wol = new WOL();
        int value;

        System.out.println("Welcome to the WOL app, created by Adrian Herrmann");

        try {
            sFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            try {
                write = new PrintWriter(file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            System.out.println("The MAC_AddressList.mac file could not be found!");
            System.out.print("Creating a new file, please enter the number of computers to be added: ");
            value = scan.nextInt();

            for (int i = 0; i < value; i++){
                System.out.print(i + 1 + "# ");
                MAC_AddressList.add(new MAC());
                MAC_AddressList.get(i).mac();

                String string = new String(MAC_AddressList.get(i).getMAC_Bytes());
                write.println(string);
            }
            write.close();
        }

    }

    public static void main(String args[]){
        new Main();
    }
}
