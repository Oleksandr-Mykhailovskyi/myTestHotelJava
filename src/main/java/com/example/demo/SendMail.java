package com.example.demo;


import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SendMail {

    @SneakyThrows
    protected void sendEmailInfo(List<Guest> serializerGuest,
                                 List<Room> rooms,
                                 int numberRoom,
                                 LocalDate dateOn,
                                 LocalDate dateOff) {

        System.out.println("Podaj adres email");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.next();

        if (isValid(email,
                serializerGuest,
                rooms,
                numberRoom,
                dateOn,
                dateOff)) {

            sendSimpleEmail(email,
                    serializerGuest,
                    rooms,
                    numberRoom,
                    dateOn,
                    dateOff);
        } else
            sendEmailInfo(serializerGuest,
                    rooms,
                    numberRoom,
                    dateOn,
                    dateOff);
    }

    protected boolean isValid(String email,
                              List<Guest> serializerGuest,
                              List<Room> rooms,
                              int numberRoom,
                              LocalDate dateOn,
                              LocalDate dateOff) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +

                "[a-zA-Z0-9_+&*-]+)*@" +

                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +

                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null)
            sendEmailInfo(serializerGuest, rooms, numberRoom, dateOn, dateOff);

        return pat.matcher(email).matches();
    }

    private static void sendSimpleEmail(String recepient,
                                        List<Guest> serializerGuest,
                                        List<Room> saveRooms,
                                        int numberRoom,
                                        LocalDate dateOn,
                                        LocalDate dateOff) throws MessagingException {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "mojtesthotel@gmail.com";
        String password = "qjvvlevbvutomoio";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMassage(session,
                myAccountEmail,
                recepient,
                serializerGuest,
                saveRooms,
                numberRoom,
                dateOn,
                dateOff);

        Transport.send(message);

    }

    private static Message prepareMassage(Session session,
                                          String myAccountEmail,
                                          String recepient,
                                          List<Guest> serializerGuest,
                                          List<Room> saveRooms,
                                          int numberRoom,
                                          LocalDate dateOn,
                                          LocalDate dateOff) {


        Message message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Hello dear " + serializerGuest.get(0).name);
            message.setText("Thank you for booking room number " +
                    saveRooms.get(numberRoom).getRoomNumber() +
                    " your price is " +
                    saveRooms.get(numberRoom).getPrice().toString() +
                    "zł, from " + dateOn +
                    " to " + dateOff);

            return message;

        }
        catch (Exception e) { e.printStackTrace(); }

        return null;
    }
}
