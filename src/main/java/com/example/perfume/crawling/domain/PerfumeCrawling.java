package com.example.perfume.crawling.domain;

public class PerfumeCrawling {
    private String perfumeName;

    public PerfumeCrawling(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    @Override
    public String toString() {
        return perfumeName;
    }
}
