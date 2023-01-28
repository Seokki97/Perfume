package com.example.perfume.perfume.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileLoading {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume3.csv";

    private List<String> perfumeListTest = new ArrayList<>();

    public InputStreamReader importFile() throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
        return inputStreamReader;
    }

    public BufferedReader readPerfumeData() throws UnsupportedEncodingException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(importFile());

        return bufferedReader;
    }

    public List<String> makePerfumeList(String[] array) {

        for (int i = 0; i < array.length; i++) {
            perfumeListTest.add(array[i]);

        }

        return perfumeListTest;
    }

    public List<String> splitPerfumeData() throws IOException {
        List<String> list = new ArrayList<>();

        while (isDataNull(readPerfumeData().readLine()) == true) {
            String[] line = readPerfumeData().readLine().split(",");
        }
        return list;
    }

    public boolean isDataNull(String data) {
        if (data == null) {

            return false;
        }
        return true;
    }

    public void testPerfumeList() throws IOException {
        splitPerfumeData();
        for (int i = 0; i < perfumeListTest.size(); i++) {
            System.out.println(perfumeListTest.get(i));

        }
    }

}
