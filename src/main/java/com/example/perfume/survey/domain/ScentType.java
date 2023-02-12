package com.example.perfume.survey.domain;

public enum ScentType {
    CITRUS("시트러스"), WOODY("우디"), FLORAL("플로럴"),
    FRUITY("프루티"), VANILLA("바닐라"), SOAPY("소피");

    private String type;
    ScentType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
