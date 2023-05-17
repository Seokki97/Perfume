package com.example.perfume.crawling.domain.perfume;

import java.util.List;
import java.util.Map;

public class CrawlingPerfume {

    private String perfumeName;

    public CrawlingPerfume(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    @Override
    public String toString() {
        return perfumeName;
    }


}

