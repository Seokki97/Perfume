package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.PerfumeBrand;
import com.example.perfume.crawling.domain.PerfumeCrawling;
import com.example.perfume.crawling.domain.PerfumeFeature;
import com.example.perfume.crawling.domain.PerfumeImage;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class CSVFileLoading {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume4.csv";
    //COLUMN_LENGTH : 향수 데이터 컬럼 개수가 늘어나면 수정해줘야함.
    private static final int COLUMN_LENGTH = 4;
    private List<String> perfumeListTest;
    private List<String> perfumeName;
    private List<String> perfumeFeature;
    private List<String> perfumeBrand;

    private List<String> perfumeImageUrl;
    private BufferedReader bufferedReader;


    public CSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {
        this.perfumeListTest = new ArrayList<>();
        this.bufferedReader = new BufferedReader(importFile());
        this.perfumeName = new ArrayList<>();
        this.perfumeBrand = new ArrayList<>();
        this.perfumeFeature = new ArrayList<>();
        this.perfumeImageUrl = new ArrayList<>();
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

    public List<String> extractPerfumeName() throws IOException {
        splitPerfumeData();

        for (int i = 0; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            perfumeName.add(perfumeListTest.get(i));
        }
        return perfumeName;
    }

    public List<String> extractPerfumeFeature() throws IOException {
        splitPerfumeData();
        for (int i = 1; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            perfumeFeature.add(perfumeListTest.get(i));
        }
        return perfumeFeature;

    }

    public List<String> extractPerfumeBrand() throws IOException {
        splitPerfumeData();
        for (int i = 2; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            perfumeBrand.add(perfumeListTest.get(i));
        }
        return perfumeBrand;

    }

    public List<String> extractPerfumeImageUrl() throws IOException {
        splitPerfumeData();
        for (int i = 3; i < perfumeListTest.size(); i+= COLUMN_LENGTH){
            perfumeImageUrl.add(perfumeListTest.get(i));
        }
        return perfumeImageUrl;
    }

    public void extractAllPerfumeData() throws IOException {
        extractPerfumeName();
        extractPerfumeFeature();
        extractPerfumeBrand();
        extractPerfumeImageUrl();
    }

    public int setMaxListSize() {
        return perfumeBrand.size();
    }
}

