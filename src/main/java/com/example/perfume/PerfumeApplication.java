package com.example.perfume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class PerfumeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PerfumeApplication.class, args);
		}
}
