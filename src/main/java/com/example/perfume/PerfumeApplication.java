package com.example.perfume;

import com.example.perfume.crawling.domain.Perfume;
import com.example.perfume.crawling.service.Clawling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		String filePath = "C:/Users/wnstj/perfume/Perfume.csv";
		File file = null;
		BufferedWriter bufferedWriter = null;

			file = new File(filePath);
			bufferedWriter = new BufferedWriter(new FileWriter(file));

			for(int i = 0 ; i< clawling.crawPerfumeFeature().size(); i++){
				String aData = "";
				aData = clawling.crawPerfumeName().get(i) +"," + clawling.crawPerfumeFeature().get(i);
				bufferedWriter.write(aData);
				bufferedWriter.newLine();

			}
		bufferedWriter.flush();
		bufferedWriter.close();

	}

}