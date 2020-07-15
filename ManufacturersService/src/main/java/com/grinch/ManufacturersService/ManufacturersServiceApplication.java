package com.grinch.ManufacturersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ManufacturersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManufacturersServiceApplication.class, args);
	}

}
