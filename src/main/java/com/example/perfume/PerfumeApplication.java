package com.example.perfume;

import com.example.perfume.perfume.service.CSVFileLoading;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;


@EnableJpaAuditing
@SpringBootApplication
public class PerfumeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PerfumeApplication.class, args);
		}
}
/*
public class PerfumeApplication {

	public static void main(String[] args) throws IOException {
        CrawlingService crawlingService = new CrawlingService();
        CsvFileConversion csvFileConversion = new CsvFileConversion();
        //씨트러스
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL7);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL8);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL9);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL10);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL11);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL12);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL13);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL14);
        csvFileConversion.makeCsvData(crawlingService, URL.CITRUS_URL15);

        csvFileConversion.makeNewLine();
        //비누향
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.SOAPY_URL6);

        csvFileConversion.makeNewLine();
        //플로랄
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL7);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL8);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL9);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL10);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL11);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL12);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL13);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL14);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL15);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL16);
        csvFileConversion.makeCsvData(crawlingService, URL.FLORAL_URL17);
        csvFileConversion.makeNewLine();
        //프루트
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.FRUITY_URL7);
        //우디
        csvFileConversion.makeNewLine();
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL7);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL8);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL9);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL10);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL11);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL12);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL13);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL14);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL15);
        csvFileConversion.makeCsvData(crawlingService, URL.WOODY_URL16);

        //스파이시
        csvFileConversion.makeNewLine();
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL7);
        csvFileConversion.makeCsvData(crawlingService, URL.SPICY_URL8);
        //바닐라
        csvFileConversion.makeNewLine();
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.VANILLA_URL6);
        //파우더
        csvFileConversion.makeNewLine();
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL2);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL3);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL4);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL5);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL6);
        csvFileConversion.makeCsvData(crawlingService, URL.POWDERY_URL7);
        csvFileConversion.bufferClose();*/
//		CSVFileLoading csvFileLoading = new CSVFileLoading();

//	}

