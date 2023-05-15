package com.example.perfume.crawling.domain.perfume;

public enum PerfumeType {
    NAME(0),FEATURE(2),BRAND(1),IMAGE(3),MAINTENANCE(4);
    private int perfumeValue;

    PerfumeType(int perfumeValue) {
        this.perfumeValue = perfumeValue;
    }

    public int selectTypeColumn() {
        return perfumeValue;
    }
}
