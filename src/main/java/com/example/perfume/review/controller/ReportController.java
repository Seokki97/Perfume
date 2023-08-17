package com.example.perfume.review.controller;

import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.service.report.ReportService;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @LoginCheck
    @PostMapping("/receive")
    public ResponseEntity<ReportResponse> receiveReport(@RequestBody ReportRequest reportRequest){
        return ResponseEntity.ok(reportService.receiveReport(reportRequest));
    }

    @LoginCheck
    @GetMapping("/processed")
    public ResponseEntity<ReportResponse> updateProcessed(@PathVariable Long reportId){
        return ResponseEntity.ok(reportService.processReport(reportId));
    }

    @LoginCheck
    @GetMapping("/reject")
    public ResponseEntity<ReportResponse> updateReject(@PathVariable Long reportId){
        return ResponseEntity.ok(reportService.rejectReport(reportId));
    }
}
