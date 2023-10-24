package com.example.perfume.review.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.service.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @LoginCheck
    @PostMapping("/receive")
    public ResponseEntity<ReportResponse> receiveReport(@RequestBody ReportRequest reportRequest) {
        log.info("리포트 접수 게시글 id : {} ",reportRequest.getReportDetail().getReportedPostUserId());
        return ResponseEntity.ok(reportService.receiveReport(reportRequest));
    }

    @LoginCheck
    @GetMapping("/processed")
    public ResponseEntity<ReportResponse> updateProcessed(@PathVariable Long reportId) {
        log.info("리포트 접수중 상태로 변경 id : {}", reportId);
        return ResponseEntity.ok(reportService.processReport(reportId));
    }

    @LoginCheck
    @GetMapping("/reject")
    public ResponseEntity<ReportResponse> updateReject(@PathVariable Long reportId) {
        log.info("리포트 거절됨 상태로 변경 id : {}", reportId);
        return ResponseEntity.ok(reportService.rejectReport(reportId));
    }
}
