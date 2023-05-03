package com.example.perfume.perfume.dto.story;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class PerfumeStoryRequest implements Serializable {
    private String name;

    private String scentAnswer;

    private String moodAnswer;

    private String seasonAnswer;

    private String styleAnswer;

    public PerfumeStoryRequest(){}

    @Builder
    public PerfumeStoryRequest(String name, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.name = name;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }

    public String toPromptString() {
        return "제가 제시할 단어는 다음과 같습니다. " +
                "사람 이름: " + name +
                ", 향수 노트: " + scentAnswer +
                ", 분위기: " + moodAnswer +
                ", 계절: " + seasonAnswer +
                ", 스타일: " + styleAnswer + "." +
                "해당 5가지의 단어들을 조합하여 400byte 이내의 짧은 스토리를 만들어주세요";
    }
}
