package com.communify.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.communify.api")
@PropertySource("classpath:application.properties")
public class CommunifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunifyApplication.class, args);
	}

}
