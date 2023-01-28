package com.example.perfume.perfume.service;

import com.example.perfume.perfume.dto.PerfumeDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class CSVFileLoading {
    private final String FILE_PATH = "C:/Users/wnstj/perfume/Perfume3.csv";

    private List<String> perfumeListTest;
    private List<String> perfumeName;
    private List<String> perfumeFeature;
    private List<String> perfumeBrand;
    private BufferedReader bufferedReader;


    public CSVFileLoading() throws FileNotFoundException, UnsupportedEncodingException {
        this.perfumeListTest = new ArrayList<>();
        this.bufferedReader = new BufferedReader(importFile());
        this.perfumeName = new ArrayList<>();
        this.perfumeBrand = new ArrayList<>();
        this.perfumeFeature = new ArrayList<>();
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
        for (int i = 0; i < perfumeListTest.size(); i += 3) {
            perfumeName.add(perfumeListTest.get(i));
        }
        return perfumeName;
    }

    public List<String> extractPerfumeFeature() throws IOException {
        splitPerfumeData();
        for (int i = 1; i < perfumeListTest.size(); i += 3) {
            perfumeFeature.add(perfumeListTest.get(i));
        }
        return perfumeFeature;

    }

    public List<String> extractPerfumeBrand() throws IOException {
        splitPerfumeData();
        for (int i = 2; i < perfumeListTest.size(); i += 3) {
            perfumeBrand.add(perfumeListTest.get(i));
        }
        return perfumeBrand;

    }

    public void extractAllPerfumeData() throws IOException {
        extractPerfumeName();
        extractPerfumeFeature();
        extractPerfumeBrand();
    }

    public int setMaxListSize() {
        return perfumeBrand.size();
    }
}
