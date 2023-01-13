package com.example.perfume.crawling.domain;

public class Perfume {
    private String perfumeName;

    private String feature;
    public Perfume(String perfumeName,String feature) {
        this.perfumeName = perfumeName;
        this.feature = feature;
    }

    @Override
    public String toString(){
        return perfumeName;
    }
}
