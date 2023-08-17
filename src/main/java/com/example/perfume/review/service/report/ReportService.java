package com.example.perfume.review.service.report;

import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportStatus;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.dto.review.requestDto.PostDeleteRequest;
import com.example.perfume.review.exception.ReportNotFoundException;
import com.example.perfume.review.repository.ReportRepository;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.ReviewBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    private final ReviewBoardRepository reviewBoardRepository;

    public ReportService(ReportRepository reportRepository, ReviewBoardRepository reviewBoardRepository) {
        this.reportRepository = reportRepository;
        this.reviewBoardRepository = reviewBoardRepository;
    }

    //신고 등록
    public ReportResponse receiveReport(ReportRequest reportRequest) {

        Report report = Report.receiveReport(reportRequest);

        reportRepository.save(report);

        return ReportResponse.convertToResponse(report);
    }

    //처리됨
    @Transactional
    public ReportResponse processReport(Long reportId) {
        Report report = reportRepository.findByReportId(reportId).orElseThrow(ReportNotFoundException::new);

        reviewBoardRepository.deleteByBoardId(report.getReportDetail().getReportedPost().getBoardId());

        report.updateStatus(ReportStatus.PROCESSED);

        return ReportResponse.convertToResponse(report);
    }

    //신고 거부
    public ReportResponse rejectReport(Long reportId) {
        Report report = reportRepository.findByReportId(reportId).orElseThrow(ReportNotFoundException::new);

        report.updateStatus(ReportStatus.REJECTED);

        return ReportResponse.convertToResponse(report);
    }
}
