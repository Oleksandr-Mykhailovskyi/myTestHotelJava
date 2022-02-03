package com.example.demo;

import lombok.SneakyThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reception {

    protected String userName;
    protected String userSurname;
    protected String userIdNumber;
    protected String days;
    protected int userNumber;
    protected int userAge;
    protected int numberRoom;

    protected Scanner scanner = new Scanner(System.in);

    protected List<Room> saveRooms = new ArrayList<>();
    protected List<Room> rooms = new ArrayList<>();

    protected List<Guest> serializerGuest = new ArrayList<>();
    protected List<Guest> guests = new ArrayList<>();

    protected LocalDate dateOn;
    protected LocalDate dateOff;

    protected RoomPrice roomPrice = new RoomPrice();
    protected Cash cash = new Cash();
    protected SendMail sendMail = new SendMail();
    protected UserInformation userInformation = new UserInformation();
    protected Serializer serializer = new Serializer();
    protected Deserializer deserializer = new Deserializer();

    protected Reception() {

        new Room(rooms);
        deserializerForSave(saveRooms);
        serializationForSave(rooms, saveRooms);
        new Guest(guests);
        serializerGuestForSave(guests);

    }

    @SneakyThrows
    protected void run() {

        System.out.println("""
                Podaj numer
                1 - Wynajmij pokój
                2 - Pokaż wolne pokoje
                3 - Pokaż pokoje
                4 - Pokaź listę gosci
                5 - Pokaż pokoje zajęte
                6 - Stan konta hotelu
                7 - wyjść""");

        getNumber();

        switch (userNumber) {
            case 1:
                bookRoom();
                run();
                break;
            case 2:
                deserializer.showUnRent(saveRooms);
                run();
                break;
            case 3:
                showRooms(rooms);
                run();
                break;
            case 4:
                deserializerGuest(guests);
                run();
                break;
            case 5:
                deserializer.showRent();
                run();
                break;
            case 6:
                showHotelMoney();
                run();
                break;
            case 7:
                scanner.close();
                System.exit(0);
            default:
                run();
        }
    }

    private void bookRoom() {

        roomChoice(saveRooms);
        dayPrice(saveRooms, numberRoom);
        breakfastPrice(saveRooms, numberRoom);
        voucher(saveRooms, numberRoom);

        checkName();
        checkSurname();
        checkUserAge();
        checkIdNumber();

        regularCostumer(numberRoom, serializerGuest, userIdNumber, days, rooms);
        addNewGuest(userName, userSurname, userAge, userIdNumber, numberRoom);
        serializationGuest(serializerGuest);
        controlOfEarnedMoney(saveRooms, numberRoom);

        reservedData();
        sendEmailInfo(serializerGuest, rooms, numberRoom, dateOn, dateOff);

        reservedRoom(numberRoom);
        serializationForSave(rooms, saveRooms);
    }

    protected List<Room> showRooms(List<Room> rooms) {
        rooms.stream()
                .forEach(room -> System.out.println(" | Numer Pokoju -> "+room.getRoomNumber()+
                        " | Cena -> "+ room.getPrice() +"zł | Ilość miejsc -> "+room.getHowManyRooms()+
                        " | Sniadania są w cenie -> "+room.getBreakfast()));
        return rooms;
    }

    protected void getNumber() {

        userInformation.getNumber();
        userNumber = userInformation.userNumber;
    }

    protected void checkName() {

        userInformation.checkName();
        userName = userInformation.userName;
    }

    protected void checkSurname() {

        userInformation.checkSurname();
        userSurname = userInformation.userSurname;
    }

    protected void checkUserAge() {

        userInformation.checkUserAge();
        userAge = userInformation.userAge;
    }

    protected void checkIdNumber() {

        userInformation.checkIdNumber();
        userIdNumber = userInformation.userIdNumber;
    }

    protected void roomChoice(List<Room> saveRooms) {

        userInformation.roomChoice(saveRooms);
        numberRoom = userInformation.numberRoom;
    }

    protected void dayPrice(List<Room> saveRooms, int numberRoom) {
        roomPrice.dayPrice(saveRooms, numberRoom);
        days = roomPrice.days;
    }

    protected void breakfastPrice(List<Room> saveRooms,
                                  int numberRoom) {

        roomPrice.breakfastPrice(saveRooms,
                numberRoom);
    }

    protected void voucher(List<Room> saveRooms,
                           int numberRoom) {

        roomPrice.voucher(saveRooms,
                numberRoom);
    }

    private void controlOfEarnedMoney(List<Room> saveRooms, int numberRoom) {

        cash.controlOfEarnedMoney(saveRooms, numberRoom);
    }

    private void showHotelMoney() {

        cash.showHotelMoney();
    }

    protected void regularCostumer(int numberRoom,
                                         List<Guest> serializerGuest,
                                         String userIdNumber,
                                         String days,
                                         List<Room> saveRooms) {

        roomPrice.regularCostumer(numberRoom,
                serializerGuest,
                userIdNumber,
                days,
                saveRooms);
    }

    @SneakyThrows
    protected void sendEmailInfo(List<Guest> serializerGuest,
                                 List<Room> rooms,
                                 int numberRoom,
                                 LocalDate dateOn,
                                 LocalDate dateOff) {

        sendMail.sendEmailInfo(serializerGuest,
                rooms,
                numberRoom,
                dateOn,
                dateOff);
    }

    private void serializationForSave(List<Room> rooms, List<Room> saveRooms) {

        serializer.serializationForSave(rooms, saveRooms);
        this.saveRooms = serializer.saveRooms;
    }

    protected void deserializerForSave(List<Room> saveRooms) {

        deserializer.deserializerForSave(saveRooms);
        this.saveRooms = deserializer.saveRooms;
    }

    private void serializationGuest(List<Guest> serializerGuest) {

        serializer.serializationGuest(serializerGuest);
    }

    private void deserializerGuest(List<Guest> serializerGuest) {

        deserializer.deserializerGuest(serializerGuest);
        this.serializerGuest = deserializer.serializerGuest;
    }

    protected void serializerGuestForSave(List<Guest> guests) {

        serializer.serializerGuestForSave(guests);
        serializerGuest = serializer.serializerGuest;
    }


    protected List<Guest> addNewGuest(String userName,
                                      String userSurname,
                                      Integer userAge,
                                      String userIdNumber,
                                      int numberRoom)
    {
        serializerGuest.add(0,
                new Guest(userName,
                        userSurname,
                        userAge,
                        userIdNumber,
                        numberRoom + 1));
        return serializerGuest;
    }

    protected List<Room> reservedRoom(int numberRoom) {

        saveRooms.get(numberRoom).setIsFree(false);
        saveRooms.get(numberRoom).setIsReady(false);
        deserializer.saveRooms.get(numberRoom).setIsReady(false);
        deserializer.saveRooms.get(numberRoom).setIsFree(false);
        serializer.saveRooms.get(numberRoom).setIsReady(false);
        serializer.saveRooms.get(numberRoom).setIsFree(false);

        System.out.println("Dziękuję! Cena za pokój : " + saveRooms.get(numberRoom).getPrice() + " | Sniadanie dostępne : " + saveRooms.get(numberRoom).getBreakfast() + " | Ilość dni : " + days);

        return saveRooms;
    }

    private void reservedData() {
        dateOn = LocalDate.now();
        dateOff = dateOn.plusDays(Long.parseLong(days));
        saveRooms.get(numberRoom).setReservedFrom(dateOn);
        saveRooms.get(numberRoom).setReservedUntil(dateOff);
    }
}