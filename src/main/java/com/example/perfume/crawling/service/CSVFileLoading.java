package com.example.perfume.crawling.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;

@Service
@Getter
public class CSVFileLoading {

    public InputStreamReader importFile(String path) throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(path);
        return new InputStreamReader(fileInputStream, "euc-kr");
    }

    public String[] splitData(String data) {
        return data.split(",");
    }


    public List<String> makeList(String[] array, List<String> targetList) {
        for (String element : array) {
            targetList.add(element);
        }
        return targetList;
    }
}
