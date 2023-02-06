package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class PerfumeCSVFileLoading {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume4.csv";
    //COLUMN_LENGTH : 향수 데이터 컬럼 개수가 늘어나면 수정해줘야함.
    private static final int COLUMN_LENGTH = 4;
    private List<String> perfumeListTest;

    private BufferedReader bufferedReader;


    public PerfumeCSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {
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

    public List<String> extractPerfumeName() throws IOException {
        splitPerfumeData();
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }

        return testList;
    }

    public List<String> extractPerfumeFeature() throws IOException {
        splitPerfumeData();
        List<String> testList = new ArrayList<>();
        for (int i = 1; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }

        return testList;
    }

    public List<String> extractPerfumeBrand() throws IOException {
        splitPerfumeData();
        List<String> testList = new ArrayList<>();
        for (int i = 2; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }

        return testList;
    }

    public List<String> extractPerfumeImageUrl() throws IOException {
        splitPerfumeData();
        List<String> testList = new ArrayList<>();
        for (int i = 3; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }

        return testList;
    }

    public PerfumeList extractAllPerfumeData(PerfumeList perfumeList) throws IOException {
        perfumeList = perfumeList.builder().perfumeName(extractPerfumeName())
                .perfumeFeature(extractPerfumeFeature())
                .perfumeBrand(extractPerfumeBrand())
                .perfumeImageUrl(extractPerfumeImageUrl())
                .build();
        return perfumeList;
    }
}

