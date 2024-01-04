package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.MaintenanceNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Maintenance {

    COLOGNE("1~3시간", "이 제품은 코롱으로 약 "),
    EA_UDE_PERFUME("5~7시간", "이 제품은 오드퍼퓸으로 약"),
    PERFUME("7~9시간", "이 제품은 퍼퓸으로 약 "),
    EA_UDE_TOILETTE("3~5시간", "이 제품은 오드 뚜왈렛으로 약 ");

    private final String maintenance;
    private final String message;

    Maintenance(String maintenance, String message) {
        this.message = message;
        this.maintenance = maintenance;
    }

    public static String findMaintenance(String perfumeMaintenance) {
        Maintenance maintenance = Arrays.stream(Maintenance.values())
                .filter(x -> x.getMaintenance().matches(perfumeMaintenance))
                .findAny().orElseThrow(MaintenanceNotFoundException::new);
        return maintenance.getMessage() + " " + maintenance.getMaintenance() + "의 지속력을 가진 제품입니다.";
    }
}
