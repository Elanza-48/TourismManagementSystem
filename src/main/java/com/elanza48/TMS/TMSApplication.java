package com.elanza48.TMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.binder.MeterBinder;

@SpringBootApplication
//@EnableCaching
public class TMSApplication {

	@Bean
	public MeterBinder processMemoryMetrics() {
		return new ProcessMemoryMetrics();
	}

	@Bean
	public MeterBinder processThreadMetrics() {
		return new ProcessThreadMetrics();
	}

	public static void main(String[] args) {
		SpringApplication.run(TMSApplication.class, args);
	}
}
