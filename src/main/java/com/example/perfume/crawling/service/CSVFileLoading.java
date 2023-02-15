package com.example.perfume.crawling.service;

import java.io.*;

public class CSVFileLoading {
    //COLUMN_LENGTH : 향수 데이터 컬럼 개수가 늘어나면 수정해줘야함.
    public final String FILE_PATH = "C:/Users/wnstj/gradu/SurveyData.csv";

    public BufferedReader bufferedReader;


    public CSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {

        this.bufferedReader = new BufferedReader(importFile());
    }

    public InputStreamReader importFile() throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
        return inputStreamReader;
    }


    public String[] splitData(String data) {
        return data.split(",");
    }

}
