package com.example.perfume.crawling.service;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CSVFileLoading {

    public InputStreamReader importFile(String path) throws UnsupportedEncodingException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
        return inputStreamReader;
    }

    public String[] splitData(String data) {
        return data.split(",");
    }


    public List<String> makeList(String[] array,List<String> targetList) {
        for (String a : array) {
            targetList.add(a);
        }
        return targetList;
    }


}
