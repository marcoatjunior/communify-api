package com.communify.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.communify.api")
public class CommunifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunifyApplication.class, args);
	}

}
