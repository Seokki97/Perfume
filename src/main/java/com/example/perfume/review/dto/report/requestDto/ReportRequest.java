package com.example.perfume.review.dto.report.requestDto;

import com.example.perfume.review.domain.report.ReportDetail;
import com.example.perfume.review.domain.report.ReportType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportRequest {

    private ReportType reportType;

    private String description;

    private ReportDetail reportDetail;

    @Builder
    public ReportRequest(ReportType reportType, String description, ReportDetail reportDetail) {
        this.reportType = reportType;
        this.description = description;
        this.reportDetail = reportDetail;
    }
}
