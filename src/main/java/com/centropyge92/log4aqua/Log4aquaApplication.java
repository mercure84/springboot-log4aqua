package com.centropyge92.log4aqua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Log4aquaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Log4aquaApplication.class, args);
	}

}
