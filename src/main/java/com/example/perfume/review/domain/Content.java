package com.example.perfume.review.domain;


import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Content {

    private String text;
    private String imageUrl;

    public Content(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }
}
