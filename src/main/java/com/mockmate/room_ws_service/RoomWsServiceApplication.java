package com.mockmate.room_ws_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class RoomWsServiceApplication {

	public static void main(String[] args) {

		// Detect the environment
		String envFile = ".env";

		// Load the appropriate .env file
		Dotenv dotenv = Dotenv.configure()
				.filename(envFile) // Specify the .env file to load
				.load();
//		Dotenv dotenv = Dotenv.load();
		System.setProperty("AUTH_SERVICE_URL", dotenv.get("AUTH_SERVICE_URL"));
		SpringApplication.run(RoomWsServiceApplication.class, args);
	}

}
