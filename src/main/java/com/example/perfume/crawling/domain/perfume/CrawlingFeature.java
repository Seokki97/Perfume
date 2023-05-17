package com.example.perfume.crawling.domain.perfume;

public class CrawlingFeature {
    private String feature;

    public CrawlingFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString(){
        return feature;
    }
}
