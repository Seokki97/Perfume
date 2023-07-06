package com.example.perfume.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.awt.*;

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
