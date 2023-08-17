package com.example.perfume.review.domain.report;

import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Enumerated(value = EnumType.STRING)
    private ReportType reportType;

    private String description;

    @CreatedDate
    private LocalDateTime reportDate;

    @Enumerated(value = EnumType.STRING)
    private ReportStatus reportStatus;

    @Embedded
    private ReportDetail reportDetail;

    @Builder
    public Report(Long reportId, ReportType reportType, String description,
                  ReportStatus reportStatus, ReportDetail reportDetail) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.description = description;
        this.reportStatus = reportStatus;
        this.reportDetail = reportDetail;
    }

    public static Report receiveReport(ReportRequest reportRequest) {
        return Report.builder()
                .description(reportRequest.getDescription())
                .reportStatus(ReportStatus.PENDING)
                .reportType(reportRequest.getReportType())
                .reportDetail(reportRequest.getReportDetail())
                .build();
    }

    public void updateStatus(ReportStatus reportStatus){
        this.reportStatus = reportStatus;
    }
}
