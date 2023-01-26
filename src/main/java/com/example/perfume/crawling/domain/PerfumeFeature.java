package com.example.perfume.crawling.domain;

public class PerfumeFeature {
    private String feature;

    public PerfumeFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString(){
        return feature;
    }
}
