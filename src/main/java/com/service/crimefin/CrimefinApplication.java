package com.service.crimefin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CrimefinApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CrimefinApplication.class, args);
		IntervalDB intervalDB = new IntervalDB();
		intervalDB.temp();
	}

}
