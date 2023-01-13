package com.example.perfume;

import com.example.perfume.crawling.Clawling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
/*
@SpringBootApplication
public class PerfumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumeApplication.class, args);
	}

}
*/

public class PerfumeApplication {

	public static void main(String[] args) throws IOException {
		Clawling clawling = new Clawling();

		clawling.CrawWebSource();
	}

}