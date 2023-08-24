package com.example.perfume.review.report;

import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportTest {

    @DisplayName("신고 상태가 변경된다.")
    @Test
    void updateStatus(){
        Report report = Report.builder()
                .reportStatus(ReportStatus.PROCESSED)
                .build();

        report.updateStatus(ReportStatus.REJECTED);

        Assertions.assertEquals(ReportStatus.REJECTED,report.getReportStatus());
    }

}
