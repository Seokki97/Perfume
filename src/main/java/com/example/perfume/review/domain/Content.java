package com.example.perfume.review.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

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
