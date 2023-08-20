package com.example.perfume.email;

import com.example.perfume.review.dto.report.requestDto.ReportRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MailDto {
    private String to;
    private String subject;
    private String message;

    @Builder
    public MailDto(String to, String subject, String message){
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public static MailDto generateReportMail(ReportRequest reportRequest){
        return MailDto.builder()
                .to(reportRequest.getReportDetail().getAdmin().getEmail())
                .subject(reportRequest.getReportType().name()+"에 관한 신고내용 입니다.")
                .message("신고 내용 입니다\n"+reportRequest.getReportDetail())
                .build();
    }
}
