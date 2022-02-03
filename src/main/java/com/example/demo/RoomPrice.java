package com.example.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class RoomPrice {
    protected String days;
    int breakfastPrice;
    int voucherPrice;
    int regularClientPrice;
    protected String payForBreakfast;
    protected String coupon;

    protected BigDecimal dayPrice(List<Room> saveRooms, int numberRoom) {
        int price;

        System.out.println("Podaj ilość dni : ");
        Scanner scanner = new Scanner(System.in);
        days = scanner.next();

        try {
            if (Integer.valueOf(days) > 0 && Integer.valueOf(days) <= 20) {
                price = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice())) * Integer.valueOf(days);
                saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(price));
            } else
                dayPrice(saveRooms, numberRoom);
        } catch (Exception ex) {
            dayPrice(saveRooms, numberRoom);
        }
        return saveRooms.get(numberRoom).getPrice();
    }

    protected int breakfastPrice(List<Room> saveRooms, int numberRoom) {

        if (saveRooms.get(numberRoom).getBreakfast().equals(false)) {
            System.out.println("""
                    Czy doliczyć sniadania do kosztu pokoju?
                    koszt śniadań wynosi 50 zł / dzień
                    Wpisz Tak lub Nie""");
            Scanner breakfast = new Scanner(System.in);
            payForBreakfast = breakfast.next();
            if (payForBreakfast.equals("tak") || payForBreakfast.equals("Tak")) {
                breakfastPrice = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice()));
                breakfastPrice = 50 * Integer.valueOf(days) + breakfastPrice;
                saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(breakfastPrice));
                saveRooms.get(numberRoom).setBreakfast(true);
            } else if (payForBreakfast.equals("nie") || payForBreakfast.equals("Nie")) {
            } else
                breakfastPrice(saveRooms, numberRoom);
        }
        return breakfastPrice;
    }

    protected Integer voucher(List<Room> saveRooms, int numberRoom) {

        System.out.println("""
                Jeśli posiadasz kupon rabatowy to podaj go
                Żeby wyjść wpisz Nie""");
        Scanner scanner = new Scanner(System.in);
        coupon = scanner.next();
        if (coupon.equals(Voucher.SUPER_HOTEL.toString())) {
            voucherPrice = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice()));
            voucherPrice = voucherPrice - (voucherPrice * 30 / 100);
            saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(voucherPrice));
            System.out.println("Otrzymałeś 30% rabat cena pokoju wynosi : " + voucherPrice + " zł");

        } else if (coupon.equals(Voucher.MEGA_HOTEL.toString())) {
            voucherPrice = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice()));
            voucherPrice = voucherPrice - (voucherPrice * 20 / 100);
            saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(voucherPrice));
            System.out.println("Otrzymałeś 20% rabat cena pokoju wynosi : " + voucherPrice + " zł");

        } else if (coupon.equals(Voucher.EXTRA_HOTEL.toString())) {
            voucherPrice = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice()));
            voucherPrice = voucherPrice - (voucherPrice * 10 / 100);
            saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(voucherPrice));
            System.out.println("Otrzymałeś 10% rabat cena pokoju wynosi : " + voucherPrice + " zł");

        } else if (coupon.equals("nie") || coupon.equals("Nie")) {}

        else
            voucher(saveRooms, numberRoom);
        return voucherPrice;
    }

    protected int regularCostumer(int numberRoom ,
                                         List<Guest> serializerGuest,
                                         String userIdNumber,
                                         String days,
                                         List<Room> saveRooms) {

        for (int i = 0; i < serializerGuest.size(); i++) {

            if (userIdNumber.equals(serializerGuest.get(i).idNumber)) {
                regularClientPrice = Integer.valueOf(String.valueOf(saveRooms.get(numberRoom).getPrice())) * Integer.valueOf(days);
                regularClientPrice = regularClientPrice - (regularClientPrice * 10 / 100);
                saveRooms.get(numberRoom).setPrice(BigDecimal.valueOf(regularClientPrice));

                System.out.println("Otrzymujesz dodatkowy rabat jako stały klient -10% na ten pokój, cena wynosi : " + regularClientPrice);

                break;
            }
        }
        return regularClientPrice;
    }
}
