package com.example.perfume.crawling.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileSerivice {
     String filePath = "C:/Users/wnstj/perfume/Perfume.csv";
     File file = new File(filePath);
     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

    public CsvFileSerivice() throws IOException {
    }
    public void makeCsvFile(ClawlingService clawlingService,String url) throws IOException {

        for (int i = 0; i < clawlingService.crawPerfumeFeature(url).size(); i++) {
            String aData = "";
            aData = clawlingService.crawPerfumeName(url).get(i) + "," + clawlingService.crawPerfumeFeature(url).get(i);
            bufferedWriter.write(aData);
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

