package com.example.perfume.crawling.service;
/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileConversion {
    private String filePath = "C:/Users/wnstj/perfume/젠더리스.csv";


    private File importedFile;
    private BufferedWriter bufferedWriter;


    public CsvFileConversion() throws IOException {
        this.importedFile = new File(filePath);
        this.bufferedWriter = new BufferedWriter(new FileWriter(importedFile));
    }

    public String getCrawlingTarget(CrawlingService crawlingService, String targetUrl, int i) throws IOException {
        return crawlingService.crawPerfumeName(targetUrl).get(i)
                + "," + crawlingService.crawPerfumeFeature(targetUrl).get(i)
                + "," + crawlingService.crawPerfumeImageUrl(targetUrl).get(i);
    }

    public void makeCsvData(CrawlingService crawlingService, String targetUrl) throws IOException {
        int listSize = crawlingService.crawPerfumeFeature(targetUrl).size();

        for (int i = 0; i < listSize; i++) {
            String crawledData = "";
            crawledData = getCrawlingTarget(crawlingService, targetUrl, i);
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

*/