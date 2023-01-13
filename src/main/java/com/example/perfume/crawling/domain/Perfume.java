package com.example.perfume.crawling.domain;

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
