package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class Guest implements Serializable {
    protected String name;
    protected String surname;
    protected int age;
    protected String idNumber;
    protected int roomNumber;

    protected Guest(List<Guest> guests) {
        guests.add(new Guest("Adam", "Baranowski", 32, "81031536647", 2));
        guests.add(new Guest("Amelia", "Adams", 25, "95061732268", 4));
        guests.add(new Guest("Michał", "Michałowski", 30, "90120180097", 6));
        guests.add(new Guest("Norbert", "Wojtowski", 18, "98011577234", 8));
        guests.add(new Guest("Damian", "Arnawa", 21, "94071700986", 9));
        guests.add(new Guest("Ania", "Kowalska", 28, "92101699087", 10));
        guests.add(new Guest("Karina", "Kołos", 23, "95011276105", 12));
        guests.add(new Guest("Adrian", "Sokołowski", 28, "92051609754", 15));
    }

    public String toString() {
        return "Name : " + name + " | Surname : " + surname + " | Age : " + age + " | ID number : " + idNumber + " | Rent room number : " + roomNumber;
    }
}
