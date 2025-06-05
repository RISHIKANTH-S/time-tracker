package com.example.TimeTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
public class TimeTrackerApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
		SpringApplication.run(TimeTrackerApplication.class, args);
	}
}
