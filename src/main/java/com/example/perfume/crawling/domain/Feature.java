package com.example.perfume.crawling.domain;

public class Feature {
    private String feature;

    public Feature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString(){
        return feature;
    }
}
