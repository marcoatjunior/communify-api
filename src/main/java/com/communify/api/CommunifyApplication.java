package com.communify.api;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@SpringBootApplication(scanBasePackages = "com.communify.api")
@PropertySource("classpath:application.properties")
public class CommunifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunifyApplication.class, args);
	}
	
	@Bean
	public VelocityEngine velocityEngine() throws Exception {
	    return new VelocityEngine(setVelocityProperties());
	}

    private Properties setVelocityProperties() {
        Properties properties = new Properties();
	    properties.setProperty("input.encoding", "UTF-8");
	    properties.setProperty("output.encoding", "UTF-8");
	    properties.setProperty("resource.loader", "class");
	    properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return properties;
    }
}
