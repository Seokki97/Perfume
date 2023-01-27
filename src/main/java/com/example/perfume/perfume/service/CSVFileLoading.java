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

    public List<String> makePerfumeList(String[] array, String line) {

        for (int i = 0; i < array.length; i++) {
            array = line.split(",");
            perfumeListTest.add(array[i]);
        }
        return perfumeListTest;
    }

    public List<String> splitPerfumeData() throws IOException {
        String line;
        List<String> list = new ArrayList<>();
        readPerfumeData();
        while ((line = readPerfumeData().readLine()) != null) {
            String[] array = line.split(",");
            list = makePerfumeList(array, line);
            perfumeListTest = makePerfumeList(array, line);
            System.out.println(perfumeListTest.get(1));
        }
        return list;
    }

    public void testPerfumeList() throws IOException {
        splitPerfumeData();
        for (int i = 0; i < perfumeListTest.size(); i++) {
            System.out.println(perfumeListTest.get(i));

        }
    }

}
