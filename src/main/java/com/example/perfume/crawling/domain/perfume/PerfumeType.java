package com.example.perfume.crawling.domain.perfume;

public enum PerfumeType {


    NAME(0),FEATURE(1),BRAND(2),IMAGE(3);

    private int perfumeValue;

    PerfumeType(int perfumeValue) {
        this.perfumeValue = perfumeValue;
    }

    public int selectTypeColumn() {
        return perfumeValue;
    }
}
