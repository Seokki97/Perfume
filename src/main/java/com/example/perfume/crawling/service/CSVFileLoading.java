package com.example.perfume.crawling.service;

import java.io.*;
import java.util.ArrayList;

public class CSVFileLoading {
    public final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume4.csv";
    //COLUMN_LENGTH : 향수 데이터 컬럼 개수가 늘어나면 수정해줘야함.


    public BufferedReader bufferedReader;

    public CSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {

        this.bufferedReader = new BufferedReader(importFile());
    }

    public InputStreamReader importFile() throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
        return inputStreamReader;
    }

}
