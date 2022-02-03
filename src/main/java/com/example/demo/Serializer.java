package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Serializer {

    protected List<Room> saveRooms = new ArrayList<>();

    protected List<Guest> serializerGuest = new ArrayList<>();

    protected void serializationForSave(List<Room> rooms, List<Room> saveRooms) {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("roomsForSave.dat"))) {

            oos.writeObject(saveRooms);
        }
        catch(Exception e){}
        if (saveRooms.size() == 0) {
            for (int i = 0; i < rooms.size(); i++) {
                saveRooms.add(rooms.get(i));
            }
        }
        this.saveRooms = saveRooms;
    }

    protected void serializationGuest(List<Guest> serializerGuest) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("guests.dat"))) {

            this.serializerGuest = serializerGuest;
            oos.writeObject(serializerGuest);
        }
        catch (Exception e) {}
        this.serializerGuest = serializerGuest;
    }

    protected List<Guest> serializerGuestForSave(List<Guest> guests) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("guests.dat"))) {
            serializerGuest = ((ArrayList<Guest>) ois.readObject());
        }
        catch (Exception e) {}
        if (serializerGuest.size() == 0) {
            for (int i = 0; i < guests.size(); i++) {
                serializerGuest.add(guests.get(i));
            }
        }
        return serializerGuest;
    }
}
