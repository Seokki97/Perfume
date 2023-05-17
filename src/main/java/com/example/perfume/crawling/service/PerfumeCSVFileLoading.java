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

    private static final int COLUMN_LENGTH = 5;
    public final String FILE_PATH = "/C:/Users/wnstj/gradu/PerfumeData.csv";
    //public final String FILE_PATH = "/home/ubuntu/data/PerfumeData.csv";
    private List<String> perfumeListTest;
    private BufferedReader bufferedReader;

    public PerfumeCSVFileLoading(List<String> perfumeListTest) throws FileNotFoundException, UnsupportedEncodingException {
        this.bufferedReader = new BufferedReader(importFile(FILE_PATH));
        this.perfumeListTest = perfumeListTest;
    }

    public List<String> splitPerfumeData() throws IOException {
        String perfumeData;
        while ((perfumeData = bufferedReader.readLine()) != null) {
            makeList(splitData(perfumeData), perfumeListTest);
        }
        return perfumeListTest;
    }

    public List<String> extractPerfumeData(int columnNumber) {
        List<String> testList = new ArrayList<>();
        for (int i = columnNumber; i < perfumeListTest.size(); i += COLUMN_LENGTH) {
            testList.add(perfumeListTest.get(i));
        }
        return testList;
    }

    public PerfumeList extractAllPerfumeData(PerfumeList perfumeList) throws IOException {
        splitPerfumeData();
        perfumeList = perfumeList.builder().perfumeName(extractPerfumeData(PerfumeType.NAME.selectTypeColumn()))
                .perfumeFeature(extractPerfumeData(PerfumeType.FEATURE.selectTypeColumn()))
                .perfumeBrand(extractPerfumeData(PerfumeType.BRAND.selectTypeColumn()))
                .perfumeImageUrl(extractPerfumeData(PerfumeType.IMAGE.selectTypeColumn()))
                .maintenance(extractPerfumeData(PerfumeType.MAINTENANCE.selectTypeColumn()))
                .build();
        return perfumeList;
    }
}

