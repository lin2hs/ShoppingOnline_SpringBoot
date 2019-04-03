package com.linhtd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShoppingOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingOnlineApplication.class, args);
	}

}
