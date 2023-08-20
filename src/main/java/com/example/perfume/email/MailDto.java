package com.example.perfume.email;

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
}
