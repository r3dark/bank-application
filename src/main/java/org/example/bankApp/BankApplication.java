package org.example.bankApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories ("org.example.bankApp")
@ComponentScan (basePackages = "org.example.bankApp")
@EntityScan ("org.example.bankApp")
@EnableAutoConfiguration
public class BankApplication {

	private static final Logger log = LogManager.getLogger(BankApplication.class);

	public static void main(String[] args) {

		log.info("Starting Bank Application...");
		SpringApplication.run(BankApplication.class, args);
	}
}
