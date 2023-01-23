package com.example.perfume.crawling.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileService {
    private String filePath = "C:/Users/wnstj/perfume/Perfume3.csv";

    private File importedFile;
    private BufferedWriter bufferedWriter;

    public CsvFileService() throws IOException {
        this.importedFile = new File(filePath);
        this.bufferedWriter = new BufferedWriter(new FileWriter(importedFile));
    }


    public void makeCsvData(CrawlingService crawlingService, String targetUrl) throws IOException {
        int listSize = crawlingService.crawPerfumeFeature(targetUrl).size();
        for (int i = 0; i < listSize; i++) {
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

    public void makeNewLine() throws IOException {
        bufferedWriter.newLine();
    }
}

