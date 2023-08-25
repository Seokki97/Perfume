package com.example.perfume.review.report;

import com.example.perfume.email.EmailSender;
import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportDetail;
import com.example.perfume.review.domain.report.ReportStatus;
import com.example.perfume.review.domain.report.ReportType;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.dto.review.responseDto.ReviewBoardResponse;
import com.example.perfume.review.repository.ReportRepository;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.report.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReviewBoardRepository reviewBoardRepository;

    @Mock
    private EmailSender emailSender;

    @DisplayName("신고한 내용이 저장된다. 접수된 내용은 관리자의 email로 보내진다.")
    @Test
    void receiveReport() {

        Report mockReport = Report.builder()
                .reportId(1l)
                .build();

        Member member = Member.builder()
                .memberId(1l)
                .email("skaksdl1238@gmail.com")
                .build();

        ReportDetail reportDetail = ReportDetail.builder()
                .administrator(member)
                .postId(1l)
                .reportedPostUserId(1l)
                .build();

        ReportRequest reportRequest = ReportRequest.builder()
                .reportDetail(reportDetail)
                .reportType(ReportType.ADVERTISEMENT)
                .build();

        when(reportRepository.save(any(Report.class))).thenReturn(mockReport);

        ReportResponse expected = reportService.receiveReport(reportRequest);
        Assertions.assertAll(
                () -> Assertions.assertEquals(mockReport.getReportId(), expected.getReportId())
        );
    }

    @DisplayName("신고한 내용이 처리 상태로 바뀐다. 이를 사용자에게 응답한다")
    @Test
    void processReport() {
        ReportDetail reportDetail = ReportDetail.builder()
                .reportedPostUserId(1l)
                .postId(1l)
                .build();
        Report mockReport = Report.builder()
                .reportStatus(ReportStatus.PENDING)
                .reportId(1l)
                .reportDetail(reportDetail)
                .build();

        when(reportRepository.findByReportId(any())).thenReturn(Optional.of(mockReport));
        doNothing().when(reviewBoardRepository).deleteByBoardId(any());

        ReportResponse reviewBoardResponse = reportService.processReport(1l);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(reviewBoardResponse.getReportStatus(),ReportStatus.PROCESSED)
        );

    }
}
