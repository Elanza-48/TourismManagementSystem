package com.elanza48.TMS;

import io.github.mweirauch.micrometer.jvm.extras.*;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableCaching
public class TourismManagementSystemApplication {

	@Bean
	public MeterBinder processMemoryMetrics() {
		return new ProcessMemoryMetrics();
	}

	@Bean
	public MeterBinder processThreadMetrics() {
		return new ProcessThreadMetrics();
	}

	public static void main(String[] args) {
		SpringApplication.run(TourismManagementSystemApplication.class, args);
	}
}
