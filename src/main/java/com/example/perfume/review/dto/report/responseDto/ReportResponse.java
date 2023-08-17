package com.example.perfume.review.dto.report.responseDto;

import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportDetail;
import com.example.perfume.review.domain.report.ReportStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class ReportResponse {

    private Long reportId;

    private String description;

    private ReportStatus reportStatus;

    private ReportDetail reportDetail;

    @Builder
    public ReportResponse(Long reportId, String description, ReportStatus reportStatus, ReportDetail reportDetail) {
        this.reportId = reportId;
        this.description = description;
        this.reportStatus = reportStatus;
        this.reportDetail = reportDetail;
    }

    public static ReportResponse convertToResponse(Report report){
        return ReportResponse.builder()
                .reportId(report.getReportId())
                .description(report.getDescription())
                .reportDetail(report.getReportDetail())
                .reportStatus(report.getReportStatus())
                .build();
    }
}
