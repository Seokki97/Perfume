package com.example.perfume.perfume.dto.story;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class PerfumeStoryRequest implements Serializable {
    private String genderAnswer;

    private String scentAnswer;

    private String moodAnswer;

    private String seasonAnswer;

    private String styleAnswer;

    @Builder
    public PerfumeStoryRequest(String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }
}
