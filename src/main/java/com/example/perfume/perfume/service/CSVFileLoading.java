package com.example.perfume.perfume.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileLoading {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume3.csv";

    private List<String> perfumeListTest;
    private BufferedReader bufferedReader;


    public CSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {
        this.perfumeListTest = new ArrayList<>();
        this.bufferedReader = new BufferedReader(importFile());
    }

    public InputStreamReader importFile() throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
        return inputStreamReader;
    }


    public List<String> makePerfumeList(String[] array) {

        for (int i = 0; i < array.length; i++) {
            perfumeListTest.add(array[i]);

        }

        return perfumeListTest;
    }

    public List<String> splitPerfumeData() throws IOException {
        String perfumeData;
        while ((perfumeData = bufferedReader.readLine()) != null) {
            String[] line = perfumeData.split(",");
            makePerfumeList(line);
        }
        return perfumeListTest;
    }

    public void testPerfumeList() throws IOException {
        splitPerfumeData();
        for (int i = 0; i < perfumeListTest.size(); i++) {
            System.out.println(perfumeListTest.get(i));
        }
    }

}
