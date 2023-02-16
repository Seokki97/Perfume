package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import com.example.perfume.crawling.domain.perfume.PerfumeType;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class PerfumeCSVFileLoading extends CSVFileLoading {

    private static final int COLUMN_LENGTH = 4;

    private List<String> perfumeListTest;
    public final String FILE_PATH = "C:/Users/wnstj/gradu/PerfumeData.csv";

    public BufferedReader bufferedReader;

    public PerfumeCSVFileLoading(List<String> perfumeListTest) throws FileNotFoundException, UnsupportedEncodingException {
        this.bufferedReader = new BufferedReader(importFile(FILE_PATH));
        this.perfumeListTest = perfumeListTest;
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
            makePerfumeList(splitData(perfumeData));
        }
        return perfumeListTest;
    }

    public List<String> extractPerfumeData(int columnNumber) throws IOException {
        splitPerfumeData();
        List<String> testList = new ArrayList<>();
        for (int i = columnNumber; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }

        return testList;
    }

    public PerfumeList extractAllPerfumeData(PerfumeList perfumeList) throws IOException {
        perfumeList = perfumeList.builder().perfumeName(extractPerfumeData(PerfumeType.NAME.selectTypeColumn()))
                .perfumeFeature(extractPerfumeData(PerfumeType.FEATURE.selectTypeColumn()))
                .perfumeBrand(extractPerfumeData(PerfumeType.BRAND.selectTypeColumn()))
                .perfumeImageUrl(extractPerfumeData(PerfumeType.IMAGE.selectTypeColumn()))
                .build();
        return perfumeList;
    }
}

