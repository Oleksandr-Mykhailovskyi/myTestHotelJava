package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyHotelApplication {

	public static void main(String[] args) {

		Reception reception = new Reception();

		SpringApplication.run(MyHotelApplication.class, args);

		reception.run();

	}

}
