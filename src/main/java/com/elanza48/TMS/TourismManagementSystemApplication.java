package com.elanza48.TMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class TourismManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismManagementSystemApplication.class, args);
	}
}
