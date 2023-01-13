package com.example.perfume;

import com.example.perfume.crawling.domain.Perfume;
import com.example.perfume.crawling.service.Clawling;

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

			System.out.println(clawling.crawPerfumeName().toString());

			System.out.println(clawling.crawPerfumeFeature().toString());

	}

}