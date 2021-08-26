package com.sber.arm;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ArmApplication {
	public static final Logger logger = LogManager.getLogger(ArmApplication.class);

	public static void main(String[] args) {
		logger.info("START: " + LocalDateTime.now());
		SpringApplication.run(ArmApplication.class, args);
	}

}
