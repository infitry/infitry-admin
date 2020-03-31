package com.infitry.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InfitryApplication {
	public static void main(String[] args) {
		SpringApplication.run(InfitryApplication.class, args);
	}
}
