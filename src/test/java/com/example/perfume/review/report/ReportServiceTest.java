package com.example.perfume.review.report;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.perfume.email.EmailSender;
import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.report.Report;
import com.example.perfume.review.domain.report.ReportDetail;
import com.example.perfume.review.domain.report.ReportStatus;
import com.example.perfume.review.domain.report.ReportType;
import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import com.example.perfume.review.dto.report.responseDto.ReportResponse;
import com.example.perfume.review.repository.ReportRepository;
import com.example.perfume.review.repository.ReviewBoardRepository;
import com.example.perfume.review.service.report.ReportService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    @DisplayName("리뷰 글이 신고되면 저장한다. 접수된 내용을 관리자의 email로 전송한다. ")
    @Test
    void receiveReport() {
        Member member = Member.builder()
                .memberId(1l)
                .email("skaksdl1238@gmail.com")
                .build();

        ReportDetail reportDetail = ReportDetail.builder()
                .administrator(member)
                .postId(1l)
                .reportedPostUserId(1l)
                .build();

        Report mockReport = Report.builder()
                .reportId(1l)
                .build();

        ReportRequest reportRequest = ReportRequest.builder()
                .description("하이")
                .reportDetail(reportDetail)
                .reportType(ReportType.ADVERTISEMENT)
                .build();

        when(reportRepository.save(any(Report.class))).thenReturn(mockReport);
        doNothing().when(emailSender).sendMail(any());

        ReportResponse expected = reportService.receiveReport(reportRequest);
        Assertions.assertAll(
                () -> Assertions.assertEquals(mockReport.getReportId(), expected.getReportId())
        );
    }

    @DisplayName("신고된 게시글의 상태가 처리중으로 변경된다. 근데 하기 싫다")
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

        ReportResponse reportResponse = reportService.processReport(1l);

        Assertions.assertAll(
                () -> Assertions.assertEquals(reportResponse.getReportStatus(), ReportStatus.PROCESSED)
        );
    }

    @Test
    @DisplayName("신고가 거절 상태로 바뀐다. 이를 사용자에게 응답한다.")
    void rejectReport() {

        Report mockReport = Report.builder()
                .reportStatus(ReportStatus.PENDING)
                .reportId(1l)
                .build();

        when(reportRepository.findByReportId(any())).thenReturn(Optional.of(mockReport));
        ReportResponse reportResponse = reportService.rejectReport(1l);

        Assertions.assertAll(
                () -> Assertions.assertEquals(reportResponse.getReportStatus(), ReportStatus.REJECTED)
        );
    }
}
