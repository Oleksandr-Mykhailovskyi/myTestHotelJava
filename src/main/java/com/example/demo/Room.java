package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class Room implements Serializable {

        private BigDecimal price;
        private LocalDate reservedFrom;
        private LocalDate reservedUntil;
        private int roomNumber;
        private int howManyRooms;
        private Boolean breakfast;
        private Boolean isFree;
        private Boolean isReady;



        protected Room(List<Room> rooms) {
                rooms.add(new Room(BigDecimal.valueOf(850), null, null, 1, 4, true, true, true));
                rooms.add(new Room(BigDecimal.valueOf(800), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 21), 2, 2, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(250), null, null, 3, 1, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(550), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 22), 4, 1, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(12800), null, null, 5, 8, true, true, true));
                rooms.add(new Room(BigDecimal.valueOf(1200), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 28), 6, 3, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(300), null, null, 7, 1, true, true, false));
                rooms.add(new Room(BigDecimal.valueOf(650), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 06, 7), 8, 2, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(400), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 22), 9, 1, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(550), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 22), 10, 1, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(15000), null, null, 11, 12, true, true, true));
                rooms.add(new Room(BigDecimal.valueOf(250), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 26), 12, 1, false, false, false));
                rooms.add(new Room(BigDecimal.valueOf(250), null, null, 13, 1, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(400), null, null, 14, 2, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(600), LocalDate.of(2021, 05, 1), LocalDate.of(2021, 05, 22), 15, 3, true, false, false));
                rooms.add(new Room(BigDecimal.valueOf(450), null, null, 16, 2, true, true, false));
                rooms.add(new Room(BigDecimal.valueOf(700), null, null, 17, 3, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(250), null, null, 18, 1, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(350), null, null, 19, 2, false, true, true));
                rooms.add(new Room(BigDecimal.valueOf(550), null, null, 20, 3, true, true, true));
        }

        public String toString(){
            return " | Numer Pokoju -> "+roomNumber+" | Cena -> "+ price +"zł | Ilość miejsc -> "+howManyRooms+" | Sniadania są w cenie -> "+breakfast;
        }
    }
