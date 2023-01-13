package com.example.perfume.crawling.domain;

import java.util.List;
import java.util.Map;

public class Perfume {
    private String perfumeName;
    public Perfume(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    @Override
    public String toString(){
        return perfumeName;
    }


}
