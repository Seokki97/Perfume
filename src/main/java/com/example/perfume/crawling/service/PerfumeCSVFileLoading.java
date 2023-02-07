package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.PerfumeList;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class PerfumeCSVFileLoading extends CSVFileLoading {


    private static final int COLUMN_LENGTH = 4;

    private static final int NAME_COLUMN = 0;
    private static final int FEATURE_COLUMN = 1;
    private static final int BRAND_COLUMN = 2;
    private static final int IMAGE_URL_COLUMN = 3;

    private List<String> perfumeListTest;

    public PerfumeCSVFileLoading(List<String> perfumeListTest) throws FileNotFoundException, UnsupportedEncodingException {

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
        perfumeList = perfumeList.builder().perfumeName(extractPerfumeData(NAME_COLUMN))
                .perfumeFeature(extractPerfumeData(FEATURE_COLUMN))
                .perfumeBrand(extractPerfumeData(BRAND_COLUMN))
                .perfumeImageUrl(extractPerfumeData(IMAGE_URL_COLUMN))
                .build();
        return perfumeList;
    }
}

