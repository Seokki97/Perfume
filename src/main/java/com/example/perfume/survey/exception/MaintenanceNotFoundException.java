package com.example.perfume.survey.exception;

import com.example.perfume.advice.BadRequestException;

public class MaintenanceNotFoundException extends BadRequestException {

    private static final String message = "해당 지속력을 찾을 수 없습니다.";

    public MaintenanceNotFoundException() {
        super(message);
    }
}
