package com.example.perfume.crawling.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileService {
     private String filePath = "C:/Users/wnstj/perfume/Perfume1.csv";

        File importedFile = new File(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(importedFile));

    public CsvFileService() throws IOException {
    }


    public void makeCsvFile(CrawlingService crawlingService, String targetUrl) throws IOException {

        for (int i = 0; i < crawlingService.crawPerfumeFeature(targetUrl).size(); i++) {
            String crawledData = "";
            crawledData = crawlingService.crawPerfumeName(targetUrl).get(i) + "," + crawlingService.crawPerfumeFeature(targetUrl).get(i);
            bufferedWriter.write(crawledData);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
    public void bufferClose() throws IOException {
        bufferedWriter.close();
    }

    public void makeNewLine() throws IOException{
        bufferedWriter.newLine();
    }
}

