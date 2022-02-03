package com.example.demo;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Cash {
    protected void controlOfEarnedMoney(List<Room> saveRooms, int numberRoom) {

        File myFile2 = new File("Budżet.txt");

        int value = 0;

        try {

            if (myFile2.createNewFile()) {
                myFile2.getName();
            }
            else {}

            FileReader file = new FileReader("Budżet.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                String use = myReader.nextLine();
                value = Integer.parseInt(use) + Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice()));
            }
            myReader.close();

            FileWriter myWriter = new FileWriter("Budżet.txt");
            myWriter.write(String.valueOf(Integer.valueOf(value)));
            myWriter.close();
        }
        catch (IOException e) { e.getMessage(); e.printStackTrace(); }
    }

    protected void showHotelMoney() {

        Scanner myReader;

        System.out.println("Podaj hasło: ");
        Scanner login = new Scanner(System.in);
        String password = login.next();

        if (password.equals("admin1234")) {
            try {

                FileReader file = new FileReader("Budżet.txt");
                myReader = new Scanner(file);

                while (myReader.hasNext()) {

                    System.out.println(myReader.next());
                }
                myReader.close();
            }
            catch (FileNotFoundException e) { e.printStackTrace(); }
        }
        else {
            System.out.println("Żle podałeś hasło");
        }
    }
}
