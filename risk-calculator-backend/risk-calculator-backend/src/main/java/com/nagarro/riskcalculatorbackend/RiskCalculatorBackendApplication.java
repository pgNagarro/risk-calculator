package com.nagarro.riskcalculatorbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RiskCalculatorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskCalculatorBackendApplication.class, args);
	}
}
