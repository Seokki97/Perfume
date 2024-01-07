package com.example.perfume.review.service.report;

import com.example.perfume.email.EmailSender;
import com.example.perfume.email.MailDto;
import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportStatus;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.exception.ReportNotFoundException;
import com.example.perfume.review.repository.ReportRepository;
import com.example.perfume.review.repository.ReviewBoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReviewBoardRepository reviewBoardRepository;

    private final EmailSender emailSender;

    public ReportService(ReportRepository reportRepository, ReviewBoardRepository reviewBoardRepository,
                         EmailSender emailSender) {
        this.reportRepository = reportRepository;
        this.reviewBoardRepository = reviewBoardRepository;
        this.emailSender = emailSender;
    }

    public ReportResponse receiveReport(ReportRequest reportRequest) {
        Report savedReport = reportRepository.save(Report.createReportObject(reportRequest));
        MailDto reportDetail = MailDto.generateReportMail(reportRequest);
        emailSender.sendMail(reportDetail);
        return ReportResponse.convertToResponse(savedReport);
    }

    @Transactional
    public ReportResponse processReport(Long reportId) {
        Report report = reportRepository.findByReportId(reportId).orElseThrow(ReportNotFoundException::new);
        reviewBoardRepository.deleteByBoardId(report.getReportDetail().getPostId());
        report.updateStatus(ReportStatus.PROCESSED);
        return ReportResponse.convertToResponse(report);
    }

    public ReportResponse rejectReport(Long reportId) {
        Report report = reportRepository.findByReportId(reportId).orElseThrow(ReportNotFoundException::new);
        report.updateStatus(ReportStatus.REJECTED);
        return ReportResponse.convertToResponse(report);
    }
}
