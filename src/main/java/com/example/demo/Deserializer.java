package com.example.demo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    protected List<Room> saveRooms = new ArrayList<>();
    protected List<Guest> serializerGuest = new ArrayList<>();

    protected void deserializerGuest(List<Guest> serializerGuest) {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("guests.dat"))) {
            serializerGuest = ((ArrayList<Guest>)ois.readObject());
        }
        catch(Exception e){}

        for(Guest p : serializerGuest)
            System.out.printf(p + "\n");
    }

    protected List<Room> deserializerForSave(List<Room> saveRooms) {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("roomsForSave.dat"))) {

            saveRooms = ((ArrayList<Room>) ois.readObject());
        }
        catch(Exception e){}

        this.saveRooms = saveRooms;

        return saveRooms;
    }

    protected void showUnRent(List<Room> saveRooms) {
        saveRooms.stream()
                .filter(room -> room.getIsFree() == true)
                .filter(room -> room.getIsReady() == true)
                .forEach(room -> System.out.println(" | Numer Pokoju -> "+room.getRoomNumber()+
                        " | Cena -> "+ room.getPrice() +"zł | Ilość miejsc -> "+room.getHowManyRooms()+
                        " | Sniadania są w cenie -> "+room.getBreakfast())
);
    }

    protected void showRent() {

        saveRooms.stream()
                .filter(room -> room.getIsFree() == false)
                .filter(room -> room.getIsReady() == false)
                .forEach(room -> System.out.println(" | Numer Pokoju -> "+room.getRoomNumber()+
                        " | Cena -> "+ room.getPrice() +"zł | Ilość miejsc -> "+room.getHowManyRooms()+
                        " | Sniadania są w cenie -> "+room.getBreakfast())
                );
    }
}
