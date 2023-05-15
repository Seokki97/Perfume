package com.example.perfume.survey.domain;

import lombok.Getter;

@Getter
public enum Maintenance {

    MAINTENANCE_FRONT("이 향수의 지속력은 "),
    MAINTENANCE_REAR("입니다. 지속력을 생각하여 향수를 뿌리시는 것을 추천드려요.");
    private final String message;

    Maintenance(String message) {
        this.message = message;
    }
}
