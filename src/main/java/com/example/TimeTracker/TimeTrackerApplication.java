package com.example.TimeTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
public class TimeTrackerApplication {
	Dotenv dotenv = Dotenv.load();
	public static void main(String[] args) {
		SpringApplication.run(TimeTrackerApplication.class, args);
	}

}
