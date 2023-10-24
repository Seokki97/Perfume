package com.example.perfume.crawling.domain;

public class PerfumeImage {
    private String perfumeImageUrl;

    public PerfumeImage(String perfumeImageUrl) {
        this.perfumeImageUrl = perfumeImageUrl;
    }

    @Override
    public String toString() {
        return perfumeImageUrl;
    }

}
