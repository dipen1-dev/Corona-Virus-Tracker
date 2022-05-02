package com.example.coronacasestracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//To enable scheduling in the application
public class CoronaCasesTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaCasesTrackerApplication.class, args);
	}

}
